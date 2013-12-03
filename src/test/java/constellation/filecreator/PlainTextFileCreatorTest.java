package constellation.filecreator;

import constellation.dbfetcher.DBFetcher;
import constellation.dbfetcher.JDBCDBFetcher;
import constellation.vo.DBInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:26 PM
 */

public class PlainTextFileCreatorTest {
    static final String DB_URL = "jdbc:derby:TESTDB";
    static DBInfo testDBInfo;

    @BeforeClass
    public static void updateDBInfo() throws SQLException {
        DBFetcher fetcher = new JDBCDBFetcher(DB_URL);
        fetcher.initDB();
        testDBInfo = fetcher.getDBInfo();
        fetcher.deInitDB();
    }

    @BeforeClass
    public static void deleteTestedFiles() throws SQLException {
        File textFile = new File("PlainTextFile");
        if(textFile.exists()){
            textFile.delete();
        }
    }

    @Test
    public void testCreateFileXML() throws Exception {
        FileCreator fileCreator = new PlainTextFileCreator();
        fileCreator.createFile(testDBInfo);
        File createdFile = new File("XMLFile");
        Assert.assertTrue(createdFile.exists());
    }

    @Test
    public void testFileXMLIsNotEmpty() throws Exception {
        FileCreator fileCreator = new PlainTextFileCreator();
        fileCreator.createFile(testDBInfo);
        File createdFile = new File("XMLFile");
        Assert.assertTrue(createdFile.length() > 0);
    }
}
