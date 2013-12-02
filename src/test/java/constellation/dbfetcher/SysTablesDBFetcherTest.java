package constellation.dbfetcher;

import constellation.vo.DBInfo;
import constellation.vo.Table;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:25 PM
 */
public class SysTablesDBFetcherTest {
    String URL = "jdbc:derby:TESTDB";

    @Test
    public void testGetDBInfoForNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo);

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoNameIsNotEmpty() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();
        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getDataBaseName(), "");
    }

    @Test
    public void testGetDBInfoNameIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getDataBaseName());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoVersionIsNotEmpty() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getDataBaseVersion(), "");

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoVersionIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getDataBaseVersion());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoSchemaNamesIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getSchemaNames());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoSchemaNamesIsNotEmpty() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getSchemaNames(), 0);

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoTableNamesIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotNull(dbInfo.getTables());

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoTableNamesIsNotEmpty() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        Assert.assertNotEquals(dbInfo.getTables(), 0);

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNamesIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertNotNull(t.getColumns());
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNamesIsNotEmpty() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertNotEquals(t.getColumns().size(), 0);
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNumberIsNotNull() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertNotEquals(t.getColumnsNumber(), 0);
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNumberIsRight() throws Exception {
        DBFetcher fetcher = new SysTablesDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertEquals(t.getColumnsNumber(), t.getColumns().size());
        }

        fetcher.deInitDB();
    }

}
