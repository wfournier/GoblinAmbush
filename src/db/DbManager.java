package db;

import java.sql.*;

public class DbManager {
    private static String hostName = "batcloud.database.windows.net";
    private static int port = 1443;
    private static String dbName = "DnD";
    private static String user = "readonly_log";
    private static String password = "1231!#ASDF!a";
    private static String url = String.format("jdbc:sqlserver://%s:%d;database=%s;user=%s@batcloud;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            hostName, port, dbName, user, password);
    private static Connection connection = null;

    public static void open() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.err.println("Error connecting to database.");
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch(SQLException ex) {
            System.err.println("Error closing connection to database.");
        }
    }

    public static ResultSet query(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                open();
            }

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            return rs;
        } catch (SQLException ex) {
            System.err.println("Error querying table.");
        }
        return null;
    }
}
