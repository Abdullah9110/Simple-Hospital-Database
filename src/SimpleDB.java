import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class SimpleDB{


    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root1234"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "university"; //database on mysql to connect to

    private static Connection con;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        DBConn a = new DBConn(URL,port, dbName, dbUsername, dbPassword);

        con = a.connectDB();
        System.out.println("Connection established");


        String SQLtxt = "select snum, sname, age, major from student";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);


        while ( rs.next() )
            System.out.println(rs.getString(1)+ " "+ rs.getString(2) + " "+
                    rs.getString(3) + " " + rs.getString(4));

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed" );

    }

}


class DBConn {

    private Connection con;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private String URL;
    private String port;
    private String dbName;

    DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {


        this.URL = URL;
        this.port = port;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        System.out.println(dbURL);

        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection (dbURL, p);
        //new com.mysql.jdbc.Driver();
        //con = DriverManager.getConnection(dbURL,p);

        return con;
    }


}