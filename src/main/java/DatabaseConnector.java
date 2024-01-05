import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://ec2-52-215-68-14.eu-west-1.compute.amazonaws.com:5432/d87mdkfi0meq7s?sslmode=require";
    private static final String USER = "wedwmqukrmmkqx";
    private static final String PASSWORD = "95b56066c94f6a6f410494f84c30a76a7ca68a6a38ab11ba87d06b508c5ea744";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
