package constellation.dbfetcher;

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
    public void initDB() throws SQLException {
        conn = DriverManager.getConnection(dbURL);
    }

    /**
     * DeInitializes the Database. Always call it after done using this object.
     */
    public void deInitDB() throws SQLException {
        conn.close();
    }

    public abstract DBInfo getDBInfo() throws SQLException;

}
