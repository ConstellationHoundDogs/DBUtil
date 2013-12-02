package constellation.dbfetcher;

import constellation.vo.Column;
import constellation.vo.DBInfo;
import constellation.vo.Table;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:13 PM
 */
public class JDBCDBFetcher extends DBFetcher {

    public JDBCDBFetcher(String dbURL) {
        super(dbURL);
    }

    /**
     * Gets the DBInfo object containing Database information, using JDBC.
     *
     * @return DBInfo  Database information
     * @throws java.sql.SQLException
     */
    @Override
    public DBInfo getDBInfo() throws SQLException {
        DBInfo dbInfo = new DBInfo();
        updateDataBaseMetaData(dbInfo);
        updateTablesContents(dbInfo);
        return dbInfo;
    }

    private void updateTablesContents(DBInfo dbInfo) throws SQLException {
        PreparedStatement statement;
        ResultSet resultSet;
        for(Table t : dbInfo.getTables()){
            statement = conn.prepareStatement("SELECT * FROM "+ t.getTableName());  // Использовать PreparedStatement здесь невозможно
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            t.setColumnsNumber(resultSetMetaData.getColumnCount());
            for(int i = 1; i <= t.getColumnsNumber(); i++){
                Column column = new Column();
                column.setColumnName(resultSetMetaData.getColumnName(i));
                column.setColumnType(resultSetMetaData.getColumnTypeName(i));
                t.getColumns().add(column);
            }
            updateColumnsContent(resultSet, t);
        }
    }

    private void updateColumnsContent(ResultSet resultSet, Table t) throws SQLException {
        while(resultSet.next()){
            for(int i = 0; i < t.getColumnsNumber(); i++){
                Column column = t.getColumns().get(i);
                column.getContent().add(resultSet.getString(i+1));
            }
        }
    }

    private void updateDataBaseMetaData(DBInfo dbInfo) throws SQLException {
        DatabaseMetaData databaseMetaData;
        databaseMetaData = conn.getMetaData();
        dbInfo.setDataBaseName(databaseMetaData.getDatabaseProductName());
        dbInfo.setDataBaseVersion(databaseMetaData.getDriverVersion());
        ResultSet schemas = databaseMetaData.getSchemas();
        while (schemas.next()){
            dbInfo.getSchemaNames().add(schemas.getString(1));
        }
        updateTablesNames(dbInfo, databaseMetaData);

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
