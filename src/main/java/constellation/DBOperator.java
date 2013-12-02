package constellation;

import constellation.vo.Column;
import constellation.vo.DBInfo;
import constellation.vo.Table;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/30/13
 * Time: 10:06 AM
 */

public class DBOperator {

    private String dbURL;
    private Connection conn;

    public DBOperator(String dbURL){
        this.dbURL = dbURL;
    }

    /**
     * Initializes the Database. Always call it after creating this object.
     */
    public void initDB(){
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            DBUtil.logger.error("Couldn't get DB connection: " + e.getMessage());
        }
    }

    /**
     * DeInitializes the Database. Always call it after done using this object.
     */
    public void deInitDB(){
        try {
            conn.close();
        } catch (SQLException e) {
            DBUtil.logger.error(e.getMessage());
        }
    }

    public DBInfo getDBInfoBySysTables(){
        DBInfo dbInfo = new DBInfo();
        try {

            updateTableNamesBySysTables(dbInfo);
            updateColumnsBySysTables(dbInfo);
            updateSchemasBySysTables(dbInfo);

        } catch (SQLException e) {
            DBUtil.logger.error(e.getMessage());
        }
        return dbInfo;
    }

    private void updateSchemasBySysTables(DBInfo dbInfo) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet schemas = statement.executeQuery("SELECT * FROM SYS.SYSSCHEMAS");
        while(schemas.next()){
            dbInfo.getSchemaNames().add(schemas.getString("SCHEMANAME"));
        }
    }

    private void updateColumnsBySysTables(DBInfo dbInfo) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        ResultSet columns = statement.executeQuery("SELECT * FROM SYS.SYSCOLUMNS");
        while(columns.next()){
            for(Table table : dbInfo.getTables()){
                if(columns.getString("REFERENCEID").equals(table.getTableID())){
                    Column column = new Column();
                    table.setColumnsNumber(0);
                    column.columnName = columns.getString("COLUMNNAME");
                    column.columnType = columns.getString("COLUMNDATATYPE");
                    int columnNumber = Integer.parseInt(columns.getString("COLUMNNUMBER"));
                    if(table.getColumnsNumber() < columnNumber){
                        table.setColumnsNumber(columnNumber);
                    }
                    table.getColumns().add(column);
                }
            }
        }
    }

    private void updateTableNamesBySysTables(DBInfo dbInfo) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        ResultSet tables = statement.executeQuery("SELECT * FROM SYS.SYSTABLES");
        while(tables.next()){
            if(tables.getString("TABLETYPE").equals("T")){
                Table newTable = new Table();
                newTable.setTableID(tables.getString("TABLEID"));
                newTable.setTableName(tables.getString("TABLENAME"));
                dbInfo.getTables().add(newTable);
            }
        }
    }


    /**
     * Gets the DBInfo object containing Database information.
     * @return DBInfo  Database information
     * @throws SQLException
     */
    public DBInfo getDBInfo() throws SQLException {
        DBInfo dbInfo = new DBInfo();
        updateDataBaseMetaData(dbInfo);
        updateTablesContents(dbInfo);
        return dbInfo;
    }

    private void updateTablesContents(DBInfo dbInfo) {
        Statement statement;
        ResultSet resultSet;
        for(Table t : dbInfo.getTables()){
            try {
                statement = conn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM " + t.getTableName());
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                t.setColumnsNumber(resultSetMetaData.getColumnCount());
                for(int i = 1; i <= t.getColumnsNumber(); i++){
                    Column column = new Column();
                    column.columnName = resultSetMetaData.getColumnName(i);
                    column.columnType = resultSetMetaData.getColumnTypeName(i);
                    t.getColumns().add(column);
                }
                updateColumnsContent(resultSet, t);
            } catch (SQLException e) {
                DBUtil.logger.error("Couldn't get statementQuery: " + e.getMessage());
            }
        }
    }

    private void updateColumnsContent(ResultSet resultSet, Table t) throws SQLException {
        while(resultSet.next()){
            for(int i = 0; i < t.getColumnsNumber(); i++){
                Column column = t.getColumns().get(i);
                column.content.add(resultSet.getString(i+1));
            }
        }
    }

    private void updateDataBaseMetaData(DBInfo dbInfo) {
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = conn.getMetaData();
            dbInfo.setDataBaseName(databaseMetaData.getDatabaseProductName());
            dbInfo.setDataBaseVersion(databaseMetaData.getDriverVersion());
            ResultSet schemas = databaseMetaData.getSchemas();
            while (schemas.next()){
                dbInfo.getSchemaNames().add(schemas.getString(1));
            }
            updateTablesNames(dbInfo, databaseMetaData);
        } catch (SQLException e) {
            DBUtil.logger.error("Couldn't get DataBaseMetaData: " + e.getMessage());
        }
    }

    private void updateTablesNames(DBInfo dbInfo, DatabaseMetaData databaseMetaData) throws SQLException {
        ResultSet tables = databaseMetaData.getTables(null, "APP", null, null);
        while (tables.next()){
            Table table = new Table();
            table.setTableName(tables.getString("TABLE_NAME"));
            dbInfo.getTables().add(table);
        }
    }

}
