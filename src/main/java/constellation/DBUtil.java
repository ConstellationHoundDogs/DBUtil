package constellation;

import constellation.dbfetcher.DBFetcher;
import constellation.dbfetcher.JDBCDBFetcher;
import constellation.filecreator.PlainTextFileCreator;
import org.apache.log4j.Logger;
import constellation.filecreator.XMLFileCreator;
import constellation.vo.DBInfo;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:31 PM
 */
public class DBUtil {

    public static Logger logger;
    String dbURL = "jdbc:derby:TESTDB";

    public static void initLogger(){
        logger = Logger.getLogger(DBUtil.class.getName());
    }

    public static void main(String[] args) throws SQLException {
        DBUtil dbUtil = new DBUtil();
        if(args.length != 0){
           dbUtil.dbURL = args[0];
        }
        dbUtil.run();
    }

    public void run() throws SQLException {
        initLogger();
        DBFetcher dbFetcher = new JDBCDBFetcher(dbURL);
        dbFetcher.initDB();

        DBInfo dbInfo = dbFetcher.getDBInfo();

        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.createFile(dbInfo);

        PlainTextFileCreator plainTextFileCreator = new PlainTextFileCreator();
        plainTextFileCreator.createFile(dbInfo);

        dbFetcher.deInitDB();
    }
}
