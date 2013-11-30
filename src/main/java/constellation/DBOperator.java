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
     *
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
     * Gets the DBInfo object containing Database information.
     * @return DBInfo  Database information
     * @throws SQLException
     */

    public DBInfo getDbInfo() throws SQLException {
        DBInfo dbInfo = new DBInfo();
        updateDataBaseMetaData(dbInfo);
        updateTablesContents(dbInfo);
        return dbInfo;
    }

    private void updateTablesContents(DBInfo dbInfo) {
        Statement statement;
        ResultSet resultSet;
        for(Table t : dbInfo.tables){
            try {
                statement = conn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM " + t.tableName);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                t.columnsNumber = resultSetMetaData.getColumnCount();
                for(int i = 1; i <= t.columnsNumber; i++){
                    Column column = new Column();
                    column.setColumnName(resultSetMetaData.getColumnName(i));
                    column.setColumnType(resultSetMetaData.getColumnTypeName(i));
                    t.columns.add(column);
                }
                updateColumnsContent(resultSet, t);
            } catch (SQLException e) {
                DBUtil.logger.error("Couldn't get statementQuery: " + e.getMessage());
            }
        }
    }

    private void updateColumnsContent(ResultSet resultSet, Table t) throws SQLException {
        while(resultSet.next()){
            for(int i = 0; i < t.columnsNumber; i++){
                Column column = t.columns.get(i);
                column.content.add(resultSet.getString(i+1));
            }
        }
    }

    private void updateDataBaseMetaData(DBInfo dbInfo) {
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = conn.getMetaData();
            dbInfo.dataBaseName = databaseMetaData.getDatabaseProductName();
            dbInfo.dataBaseVersion = databaseMetaData.getDriverVersion();
            ResultSet schemas = databaseMetaData.getSchemas();
            while (schemas.next()){
                dbInfo.schemaNames.add(schemas.getString(1));
            }
            ResultSet tables = databaseMetaData.getTables(null, "APP", null, null);
            while (tables.next()){
                Table table = new Table();
                table.tableName = tables.getString("TABLE_NAME");
                dbInfo.tables.add(table);
            }
        } catch (SQLException e) {
            DBUtil.logger.error("Couldn't get DataBaseMetaData: " + e.getMessage());
        }
    }


}
