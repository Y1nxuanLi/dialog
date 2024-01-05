import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://ec2-52-215-68-14.eu-west-1.compute.amazonaws.com:5432/d87mdkfi0meq7s?sslmode=require";
    private static final String USER = "wedwmqukrmmkqx";
    private static final String PASSWORD = "95b56066c94f6a6f410494f84c30a76a7ca68a6a38ab11ba87d06b508c5ea744";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    /*public static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        System.out.println(dbUri);
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        System.out.println(dbUrl);
        return DriverManager.getConnection(dbUrl, username, password);
    }*/

    /*String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try (Connection conn = DriverManager.getConnection(dbUrl);
    Statement s = conn.createStatement();
    ResultSet rset = s.executeQuery("SELECT * FROM patients WHERE id > 1;")) {

        while (rset.next()) {
            // You can modify this part to send back data to the client
            System.out.println(rset.getInt("id") + " " + rset.getString("familyname"));
            resp.getWriter().write(rset.getInt("id") + " " + rset.getString("familyname"));
        }
    } catch (SQLException e) {
        // Proper exception handling here
        e.printStackTrace(); // Consider a logging framework
    }*/


}
