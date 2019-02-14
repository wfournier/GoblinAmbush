package db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;

public class DbManager {
    private static String serverName = "batcloud.database.windows.net";
    private static int port = 1433;
    private static String dbName = "DnD";
    private static String user = "readonly_log";
    private static String password = "1231!#ASDF!a";
    //    private static String url = String.format("jdbc:sqlserver://%s:%d;database=%s;user=%s@batcloud;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
//            serverName, port, dbName, user, password);
    private static Connection connection = null;

    public static void open() {
        SQLServerDataSource ds = new SQLServerDataSource();
//        ds.setURL(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setServerName(serverName);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        try {
            connection = ds.getConnection();
        } catch (SQLException ex) {
            System.err.println("Error connecting to database.");
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Error closing connection to database.");
        }
    }

    public static ResultSet query(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                open();
            }

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error querying table.");
        }
        return null;
    }
}
