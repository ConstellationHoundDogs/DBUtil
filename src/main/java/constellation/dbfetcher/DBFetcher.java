package constellation.dbfetcher;

import constellation.DBUtil;
import constellation.vo.DBInfo;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/30/13
 * Time: 10:06 AM
 */
public abstract class DBFetcher {

    private String dbURL;
    Connection conn;

    public DBFetcher(String dbURL){
        this.dbURL = dbURL;
    }

    /**
     * Initializes the Database. Always call it after creating this object.
     */
    public void initDB(){
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            DBUtil.logger.error("Couldn't get DB connection: " + e.getMessage());
        }
    }

    /**
     * DeInitializes the Database. Always call it after done using this object.
     */
    public void deInitDB(){
        try {
            conn.close();
        } catch (SQLException e) {
            DBUtil.logger.error(e.getMessage());
        }
    }

    public abstract DBInfo getDBInfo() throws SQLException;

}
