package constellation.dbfetcher;

import constellation.vo.DBInfo;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:25 PM
 */
public class JDBCDBFetcherTest {

    String URL = "jdbc:derby:TESTDB";

    @Test
    public void testGetDBInfoForNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo);

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoNameIsNotEmpty() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();
        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getDataBaseName(), "");
    }

    @Test
    public void testGetDBInfoNameIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getDataBaseName());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoVersionIsNotEmpty() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getDataBaseVersion(), "");

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoVersionIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getDataBaseVersion());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoSchemaNamesIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getSchemaNames());

        fetcher.deInitDB();
    }


    @Test
    public void testGetDBInfoSchemaNamesIsNotEmpty() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getSchemaNames(), 0);

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoTableNamesIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getTables());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoTableNamesIsNotEmpty() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getTables(), 0);

        fetcher.deInitDB();
    }

}
