package constellation.dbfetcher;

import constellation.vo.DBInfo;
import constellation.vo.Table;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:25 PM
 */
public class JDBCDBFetcherTest {

    //Тестировка в данном классе производиться с базой данных в которой есть таблицы, и они не пустые,
    //а в этих таблицах есть колонки, и они не пустые,
    //дабы проверить работу методов которые заполняют обьект хранения информации (DBInfo)

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

    @Test
    public void testGetDBInfoColumnNamesIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
           Assert.assertNotNull(t.getColumns());
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNamesIsNotEmpty() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertNotEquals(t.getColumns().size(), 0);
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNumberIsNotNull() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertNotEquals(t.getColumnsNumber(), 0);
        }

        fetcher.deInitDB();
    }

    @Test
    public void testGetDBInfoColumnNumberIsRight() throws Exception {
        JDBCDBFetcher fetcher = new JDBCDBFetcher(URL);
        fetcher.initDB();

        DBInfo dbInfo = fetcher.getDBInfo();
        for(Table t : dbInfo.getTables()){
            Assert.assertEquals(t.getColumnsNumber(), t.getColumns().size());
        }

        fetcher.deInitDB();
    }

}
