package constellation.filecreator;

import constellation.dbfetcher.DBFetcher;
import constellation.dbfetcher.JDBCDBFetcher;
import constellation.vo.DBInfo;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/2/13
 * Time: 7:27 PM
 */
public class XMLFileCreatorTest {

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
        File xmlFile = new File("XMLFile");
        if(xmlFile.exists()){
           xmlFile.delete();
        }
    }

    @Test
    public void testCreateFileXML() throws Exception {
        FileCreator fileCreator = new XMLFileCreator();
        fileCreator.createFile(testDBInfo);
        File createdFile = new File("XMLFile");
        Assert.assertTrue(createdFile.exists());
    }

    @Test
    public void testFileXMLIsNotEmpty() throws Exception {
        FileCreator fileCreator = new XMLFileCreator();
        fileCreator.createFile(testDBInfo);
        File createdFile = new File("XMLFile");
        Assert.assertTrue(createdFile.length() > 0);
    }

    @Test
    public void testIfFileIsXML() throws ParserConfigurationException, IOException, SAXException, JAXBException {
        FileCreator fileCreator = new XMLFileCreator();
        fileCreator.createFile(testDBInfo);
        File createdFile = new File("XMLFile");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(String.valueOf(createdFile)));
    }
}
