package DiaLogSQL;

import DiaLogServlet.DatabaseConnector;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class UserLoginDataSQL {
    public static void createTable() {
        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userLogin (" +
                "id SERIAL PRIMARY KEY, " +
                "userAccount VARCHAR(128) NOT NULL, " +
                "userPassword VARCHAR(128) NOT NULL;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("UserLoginDataSQL Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String acc, String pswd) {
        String sqlInsert = "INSERT INTO userLogin (userAccount, userPassword) VALUES (?, ?);";
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
        String sqlSelect = "SELECT * FROM userLogin;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String userAccount = rs.getString("userAccount");
                String userPassword = rs.getString("userPassword");
                resp.getWriter().write("ID: " + id + ", Account: " + userAccount + ", Password: " + userPassword+ ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int checkIdentity(String acc, String pswd) throws SQLException {
        String sql = "SELECT * FROM userLogin WHERE userAccount = ? AND userPassword = ?;";
        System.out.println("Checking Identity.");
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setting the parameters with user inputs
            pstmt.setString(1, acc);
            pstmt.setString(2, pswd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // If a record is found, return the id
                    return rs.getInt("id");
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

}


