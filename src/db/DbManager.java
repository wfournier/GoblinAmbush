package db;

import java.sql.*;

public class DbManager {
    private static String hostName = "batcloud.database.windows.net";;
    private static String dbName = "DnD";;
    private static String user = "wfournier";;
    private static String password = "AeJnr50*RY@o";;
//    private static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
//            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
    private static String url = "Server=tcp:batcloud.database.windows.net,1433;Initial Catalog=DnD;Persist Security Info=False;User ID=wfournier;Password=AeJnr50*RY@o;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";
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
