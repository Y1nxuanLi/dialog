/*

Connect Java servlet to Heroku PostgreSQL Database mini

Note: Heroku's automated database maintenance could result in changes to the database credentials,
which disconnecting our app from previous database.
In the event that this issue arises, manually update the URL USER PASSWORD below.

Parameter can be obtained from Heroku PostgreSQL Database/Settings/Database Credentials

Current:
Host
    ec2-34-242-199-141.eu-west-1.compute.amazonaws.com
Database
    d1bp839nq4ke7i
User
    oiiornnyanreha
Port
    5432
Password
    1df465336d513aa195ed96c495f2cd277be5ff07dcce136bb3c7bfee5d18d9c8
URI
    postgres://oiiornnyanreha:1df465336d513aa195ed96c495f2cd277be5ff07dcce136bb3c7bfee5d18d9c8@ec2-34-242-199-141.eu-west-1.compute.amazonaws.com:5432/d1bp839nq4ke7i
Heroku CLI
    heroku pg:psql postgresql-cylindrical-48616 --app dialog

 */

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
