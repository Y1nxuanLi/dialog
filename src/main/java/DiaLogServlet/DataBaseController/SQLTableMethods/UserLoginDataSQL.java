package DiaLogServlet.DataBaseController.SQLTableMethods;

import DiaLogServlet.DataBaseController.DatabaseConnector;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class UserLoginDataSQL {
    public static void createTable() {
        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userLoginData (" +
                "userID SERIAL PRIMARY KEY, " +
                "userAccount VARCHAR(128) NOT NULL, " +
                "userPassword VARCHAR(128) NOT NULL);";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("UserLoginDataSQL Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String acc, String pswd) {
        String sqlInsert = "INSERT INTO userLoginData (userAccount, userPassword) VALUES (?, ?);";
        System.out.println("Inserting Data table.");
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setString(1, acc);
            pstmt.setString(2, pswd);

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayData(HttpServletResponse resp) {
        String sqlSelect = "SELECT * FROM userLoginData;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            System.out.println("Display SQL success.");
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String userAccount = rs.getString("userAccount");
                String userPassword = rs.getString("userPassword");
                resp.getWriter().write("userID: " + userID + ", Account: " + userAccount + ", Password: " + userPassword+ ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int checkIdentity(String acc, String pswd) throws SQLException {
        String sqlCheck = "SELECT * FROM userLoginData WHERE userAccount = ? AND userPassword = ?;";
        System.out.println("Checking Identity.");
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {

            // Setting the parameters with user inputs
            pstmt.setString(1, acc);
            pstmt.setString(2, pswd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // If a record is found, return the id
                    return rs.getInt("userID");
                } else {
                    // If no record is found, return null or throw an exception as per your design decision
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Depending on how you handle exceptions, you might want to rethrow it or handle it differently
        }
        return 0;
    }
    public static void deleteUser(int userID) {
        System.out.println("Deleting record from userLoginData table.");
        String sqlDelete = "DELETE FROM userLoginData WHERE userID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            System.out.println("Delete success.");
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject readUser(int userID) {
        System.out.println("Reading data from userLoginData table.");
        String sqlRead = "SELECT * FROM userLoginData WHERE userID = ?";
        JsonObject jsonData = new JsonObject();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRead)) {

            pstmt.setInt(1, userID); // Set the userID parameter here, before executing the query

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                System.out.println("Read SQL success.");
                if (rs.next()) { // Use if instead of while if you're expecting only one result
                    jsonData.addProperty("userID", rs.getInt("userID"));
                    jsonData.addProperty("userAccount", rs.getString("userAccount"));
                    jsonData.addProperty("userPassword", rs.getString("userPassword"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonData;
    }


}

