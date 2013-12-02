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
    public void testInitDB() throws Exception {
        String testDBURL = "This DB url is not right.";
        DBFetcher dbFetcher = new JDBCDBFetcher(testDBURL);
    }

    @Test
    public void testDeInitDB() throws Exception {

    }
}
