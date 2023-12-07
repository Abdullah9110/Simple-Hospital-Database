package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {

    private static Connection conn;
    static String URL = "127.0.0.1";
    static String port = "3306";
    static String dbName = "hospital";
    static String dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
    static String dbUsername = "root";
    static String dbPassword = "2263";

    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }

        conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
}
