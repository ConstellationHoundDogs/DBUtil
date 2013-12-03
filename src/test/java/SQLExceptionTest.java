import constellation.DBUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/30/13
 * Time: 12:16 AM
 */

public class SQLExceptionTest {

    static final String DB_URL = "jdbc:derby:TESTDB";
    static Connection conn;
    static Statement stmnt;

    @BeforeClass
    public static void initLogger(){
        DBUtil.initLogger();
    }

    @BeforeClass
    public static void initDB(){
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            DBUtil.logger.error("[TEST] " + e.getMessage());
        }
        try {
            stmnt = conn.createStatement();
        } catch (SQLException e) {
            DBUtil.logger.error("[TEST] " + e.getMessage());
        }
    }

    @Test (expected = SQLNonTransientException.class)
    public void nonTransientException() throws SQLException {
        stmnt.executeQuery("This query is not right.");
    }

    @Test  (expected = SQLTransientException.class)
    public void transientDBException() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        stmnt.setQueryTimeout(1);
        try{
            stmnt.execute("DROP FUNCTION DELAY");
        }catch (SQLException e){
            DBUtil.logger.info("[TEST] " + e.getMessage());
        }
        stmnt.execute("CREATE FUNCTION DELAY(SECONDS INTEGER, VALUE INTEGER) RETURNS INTEGER PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA EXTERNAL NAME 'SQLExceptionTest.delay'");
        ResultSet resultSet = stmnt.executeQuery("SELECT column1 FROM mytable where mod(DELAY(3,column1),2)=0");
        while(resultSet.next()){
            DBUtil.logger.info("[TEST] resultSet has received \"next\" call");
        }
    }

    public static int delay(int seconds, int value){
        try{
            Thread.sleep(seconds * 1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return value;
    }

    @AfterClass
    public static void deInitDB(){
        try {
            stmnt.close();
        } catch (SQLException e) {
            DBUtil.logger.error("[TEST] " + e.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            DBUtil.logger.error("[TEST] " + e.getMessage());
        }
    }

}
