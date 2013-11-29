package constellation;

import org.apache.log4j.Logger;
import constellation.filecreator.XMLFileCreator;
import constellation.vo.Column;
import constellation.vo.DBInfo;
import constellation.vo.Table;

import java.sql.*;


/** Что ж, прекрасно-прекрасно, только вот у Вас здесь ошибочка получилась, компилятор её не видет, но я заметил... ВСЕ ПЕРЕДЕЛАТЬ!!!
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:31 PM
 */
public class DBUtil {
    public static Logger logger;

    Connection conn;
    String dbURL = "jdbc:derby:TESTDB";


    public static void main(String... args) throws SQLException {
        DBUtil dbUtil = new DBUtil();
        dbUtil.run();
    }

    public void run() throws SQLException {
        initLogger();
        initDB();
        DBInfo dbInfo = getDbInfo();
        textDBInfoOutput(dbInfo);
        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.createFile(dbInfo);
    }


    private void textDBInfoOutput(DBInfo dbInfo) {
        System.out.println(dbInfo.dataBaseName);
        System.out.println(dbInfo.dataBaseVersion);

        for(Table t : dbInfo.tables){
            System.out.println(t.tableName);
            System.out.println(t.columnsNumber);
            for(int i = 0; i < t.columns.size(); i++){
                System.out.print(t.columns.get(i).columnType + " ");
            }
            System.out.println();
            for(int i = 0; i < t.columns.get(0).content.size(); i++){
                for(int j = 0; j < t.columnsNumber; j++){
                    System.out.print(t.columns.get(j).content.get(i) + " ");
                }
                System.out.println();
            }
        }
    }

    private void initDB(){
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            logger.error("Couldn't get DB connection: "+ e.getMessage());
        }
    }

    private void initLogger(){
         logger = Logger.getLogger(DBUtil.class.getName());
    }

    private DBInfo getDbInfo() throws SQLException {
        DBInfo dbInfo = new DBInfo();

        getDataBaseMetaData(dbInfo);

        getTablesContents(dbInfo);


        return dbInfo;
    }

    private void getTablesContents(DBInfo dbInfo) {
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
                    column.columnName = resultSetMetaData.getColumnName(i);
                    column.columnType = resultSetMetaData.getColumnTypeName(i);
                    t.columns.add(column);
                }
                getColumnsContent(resultSet, t);
            } catch (SQLException e) {
                logger.error("Couldn't get statementQuery: " + e.getMessage());
            }
        }
    }

    private void getColumnsContent(ResultSet resultSet, Table t) throws SQLException {
        while(resultSet.next()){
            for(int i = 0; i < t.columnsNumber; i++){
                Column column = t.columns.get(i);
                column.content.add(resultSet.getString(i+1));
            }
        }
    }

    private void getDataBaseMetaData(DBInfo dbInfo) {
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
            logger.error("Couldn't get DataBaseMetaData: " + e.getMessage());
        }

    }

}
