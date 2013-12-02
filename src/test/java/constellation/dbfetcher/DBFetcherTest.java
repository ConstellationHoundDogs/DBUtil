package constellation.dbfetcher;

import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:24 PM
 */
public class DBFetcherTest {

    @Test (expected = SQLException.class)
    public void testInitDBJDBCNotRightURL() throws Exception {
        String testDBURL = "This DB url is not right.";
        DBFetcher dbFetcher = new JDBCDBFetcher(testDBURL);
        dbFetcher.initDB();
    }

    @Test (expected = SQLException.class)
    public void testDeInitDBJDBCNotRightURL() throws Exception {
        String testDBURL = "This DB url is not right.";
        DBFetcher dbFetcher = new JDBCDBFetcher(testDBURL);
        dbFetcher.initDB();
    }

    @Test (expected = SQLException.class)
    public void testInitDBSysTablesNotRightURL() throws Exception {
        String testDBURL = "This DB url is not right.";
        DBFetcher dbFetcher = new SysTablesDBFetcher(testDBURL);
        dbFetcher.initDB();
    }

    @Test (expected = SQLException.class)
    public void testDeInitDBSysTablesNotRightURL() throws Exception {
        String testDBURL = "This DB url is not right.";
        DBFetcher dbFetcher = new SysTablesDBFetcher(testDBURL);
        dbFetcher.initDB();
    }

}
