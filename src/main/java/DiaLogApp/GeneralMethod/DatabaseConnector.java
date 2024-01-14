package DiaLogApp.GeneralMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    //private static final String URL = "jdbc:postgresql://your_host:your_port/your_database?sslmode=require";
    private static final String URL = "jdbc:postgresql://ec2-34-242-199-141.eu-west-1.compute.amazonaws.com:5432" +
            "/d1bp839nq4ke7i?sslmode=require";

    private static final String USER = "oiiornnyanreha";
    private static final String PASSWORD = "1df465336d513aa195ed96c495f2cd277be5ff07dcce136bb3c7bfee5d18d9c8";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
