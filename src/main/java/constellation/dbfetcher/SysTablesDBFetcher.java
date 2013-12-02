package constellation.dbfetcher;

import constellation.DBUtil;
import constellation.vo.Column;
import constellation.vo.DBInfo;
import constellation.vo.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 6:48 PM
 */
public class SysTablesDBFetcher extends DBFetcher {

    public SysTablesDBFetcher(String dbURL) {
        super(dbURL);
    }

    /**
     * Gets the DBInfo object containing Database information, using system tables.
     *
     * @return DBInfo  Database information
     * @throws java.sql.SQLException
     */
    @Override
    public DBInfo getDBInfo() throws SQLException {
        DBInfo dbInfo = new DBInfo();

        updateTableNames(dbInfo);
        updateColumns(dbInfo);
        updateSchemas(dbInfo);

        return dbInfo;
    }

    private void updateSchemas(DBInfo dbInfo) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet schemas = statement.executeQuery("SELECT * FROM SYS.SYSSCHEMAS");
        while(schemas.next()){
            dbInfo.getSchemaNames().add(schemas.getString("SCHEMANAME"));
        }
    }

    private void updateColumns(DBInfo dbInfo) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        ResultSet columns = statement.executeQuery("SELECT * FROM SYS.SYSCOLUMNS");
        while(columns.next()){
            for(Table table : dbInfo.getTables()){
                if(columns.getString("REFERENCEID").equals(table.getTableID())){
                    Column column = new Column();
                    table.setColumnsNumber(0);
                    column.setColumnName(columns.getString("COLUMNNAME"));
                    column.setColumnName(columns.getString("COLUMNDATATYPE"));
                    int columnNumber = Integer.parseInt(columns.getString("COLUMNNUMBER"));
                    if(table.getColumnsNumber() < columnNumber){
                        table.setColumnsNumber(columnNumber);
                    }
                    table.getColumns().add(column);
                }
            }
        }
    }

    private void updateTableNames(DBInfo dbInfo) throws SQLException {
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
}
