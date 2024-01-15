/*

SQL Handling Method Class for userData

make sure the field name is EXACTLY the same with the request send from client.

 */

package DiaLogServlet.UserServlet;

import DiaLogApp.GeneralMethod.DatabaseConnector;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataSQL {
    public static void createTable() {
        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userData (" +
                "userID SERIAL PRIMARY KEY, " +
                "userAccount VARCHAR(128) NOT NULL, " +
                "userPassword VARCHAR(128) NOT NULL, " +

                "userConfirmedPassword VARCHAR(128), " +

                "id VARCHAR(128), " +
                "userName VARCHAR(128), " +
                "address VARCHAR(256), " +
                "email VARCHAR(128), " +
                "gender VARCHAR(50), " +
                "diabetesType VARCHAR(50), " +
                "insulinType VARCHAR(50), " +
                "phoneNumber VARCHAR(20), " +
                "doctorNumber VARCHAR(20), " +
                "postalCode VARCHAR(20));";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("UserData Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertData(UserData userData) {
        String sqlInsert = "INSERT INTO userData (" +
                "userAccount, userPassword, userConfirmedPassword, id, userName, address, " +
                "email, gender, diabetesType, insulinType, phoneNumber, doctorNumber, postalCode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        System.out.println("Inserting Data into table.");

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setString(1, userData.getUserAccount());
            pstmt.setString(2, userData.getUserPassword());
            pstmt.setString(3, userData.getUserConfirmedPassword());
            pstmt.setString(4, userData.getId());
            pstmt.setString(5, userData.getUserName());
            pstmt.setString(6, userData.getAddress());
            pstmt.setString(7, userData.getEmail());
            pstmt.setString(8, userData.getGender());
            pstmt.setString(9, userData.getDiabetesType());
            pstmt.setString(10, userData.getInsulinType());
            pstmt.setString(11, userData.getPhoneNumber());
            pstmt.setString(12, userData.getDoctorNumber());
            pstmt.setString(13, userData.getPostalCode());

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void displayUserData(HttpServletResponse resp) {
        String sqlSelect = "SELECT * FROM userData;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            System.out.println("Display SQL success.");

            // Initialize a list to hold UserData objects
            List<UserData> users = new ArrayList<>();

            while (rs.next()) {
                UserData user = new UserData();
                user.setUserID(rs.getInt("userID"));
                user.setId(rs.getString("id"));
                user.setUserAccount(rs.getString("userAccount"));
                user.setUserPassword(rs.getString("userPassword"));


                user.setUserConfirmedPassword(rs.getString("userConfirmedPassword"));
                user.setUserName(rs.getString("userName"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setDiabetesType(rs.getString("diabetesType"));
                user.setInsulinType(rs.getString("insulinType"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setDoctorNumber(rs.getString("doctorNumber"));
                user.setPostalCode(rs.getString("postalCode"));

                users.add(user);
            }

            // Convert the list of users to JSON
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(users);
            String responseWithText = "UserLoginData Display for testing purpose: \n" + jsonResponse;

            // Set the response type to JSON
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(responseWithText);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int checkIdentity(String acc, String pswd) throws SQLException {
        String sqlCheck = "SELECT * FROM userData WHERE userAccount = ? AND userPassword = ?;";
        System.out.println("Checking Identity.");
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {

            // Setting the parameters with user inputs
            pstmt.setString(1, acc);
            pstmt.setString(2, pswd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // If a record is found, return the userID
                    return rs.getInt("userID");
                } else {
                    // If no record is found, return 0
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Depending on how you handle exceptions, you might want to rethrow it or handle it differently
        }
        return 0;
    }

    public static void updateAllUser(UserData userData) {
        System.out.println("Updating user in userData table.");
        String sqlUpdate = "UPDATE userData SET userAccount = ?, userPassword = ?, userConfirmedPassword = ?, id = ?, userName = ?, address = ?, email = ?, gender = ?, diabetesType = ?, insulinType = ?, phoneNumber = ?, doctorNumber = ?, postalCode = ? WHERE userID = ?;";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setString(1, userData.getUserAccount());
            pstmt.setString(2, userData.getUserPassword());
            pstmt.setString(3, userData.getUserConfirmedPassword());
            pstmt.setString(4, userData.getId());
            pstmt.setString(5, userData.getUserName());
            pstmt.setString(6, userData.getAddress());
            pstmt.setString(7, userData.getEmail());
            pstmt.setString(8, userData.getGender());
            pstmt.setString(9, userData.getDiabetesType());
            pstmt.setString(10, userData.getInsulinType());
            pstmt.setString(11, userData.getPhoneNumber());
            pstmt.setString(12, userData.getDoctorNumber());
            pstmt.setString(13, userData.getPostalCode());
            pstmt.setInt(14, userData.getUserID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("No user was updated. Check the userID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int updateUser(UserData userData) {
        System.out.println("Updating user in userData table.");
        String sqlUpdate = "UPDATE userData SET userName = ?, address = ?, email = ?, gender = ?, diabetesType = ?, insulinType = ?, phoneNumber = ?, doctorNumber = ?, postalCode = ? WHERE userID = ?;";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setString(1, userData.getUserName());
            pstmt.setString(2, userData.getAddress());
            pstmt.setString(3, userData.getEmail());
            pstmt.setString(4, userData.getGender());
            pstmt.setString(5, userData.getDiabetesType());
            pstmt.setString(6, userData.getInsulinType());
            pstmt.setString(7, userData.getPhoneNumber());
            pstmt.setString(8, userData.getDoctorNumber());
            pstmt.setString(9, userData.getPostalCode());
            String currentId = userData.getId();
            int currentId_int = Integer.parseInt(currentId);
            pstmt.setInt(10, currentId_int);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
                return 1;
            } else {
                System.out.println("No user was updated. Check the userID.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void deleteUser(int userID) {
        System.out.println("Deleting record from userData table.");
        String sqlDelete = "DELETE FROM userData WHERE userID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
            System.out.println("Delete success.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static JsonObject readUser(int userID) {
        System.out.println("Reading data from userData table.");
        String sqlRead = "SELECT * FROM userData WHERE userID = ?";
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
                    // Add new fields from the userData table
                    jsonData.addProperty("userConfirmedPassword", rs.getString("userConfirmedPassword"));
                    jsonData.addProperty("id", rs.getString("id"));
                    jsonData.addProperty("userName", rs.getString("userName"));
                    jsonData.addProperty("address", rs.getString("address"));
                    jsonData.addProperty("email", rs.getString("email"));
                    jsonData.addProperty("gender", rs.getString("gender"));
                    jsonData.addProperty("diabetesType", rs.getString("diabetesType"));
                    jsonData.addProperty("insulinType", rs.getString("insulinType"));
                    jsonData.addProperty("phoneNumber", rs.getString("phoneNumber"));
                    jsonData.addProperty("doctorNumber", rs.getString("doctorNumber"));
                    jsonData.addProperty("postalCode", rs.getString("postalCode"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonData;
    }



}


