package DiaLogServlet.DataBaseController.SQLTableMethods;

import DiaLogServlet.DataBaseController.DatabaseConnector;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LogDataSQL {
    private Long id;
    private String content;
    private String title;
    private Long userId;
    private String createTime;
    private String updateTime;
    public static void createTable() {

        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS logData (" +
                // Simple
                "id SERIAL PRIMARY KEY, " +
                "userID INT NOT NULL, " +
                "bloodSugar VARCHAR(128), " +

                "notes VARCHAR(128), " +
                "createTime VARCHAR(128), " +
                "updateTime VARCHAR(128), " +
                "logType INT, " +

                //Comprehensive
                "carb VARCHAR(128), " +
                "mealDescription VARCHAR(128), " +
                "insulinDose VARCHAR(128), " +
                "medication VARCHAR(128), " +
                "exerciseDescription VARCHAR(128), " +

                //Intensive
                "exerciseType VARCHAR(128), " +
                "exerciseDuration VARCHAR(128), " +
                "insulinType VARCHAR(128), " +

                "logType INT; ";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("taskData Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(int userID, String bloodSugar, String notes, String createTime, String updateTime, int logType, String carb, String mealDescription, String insulinDose, String medication, String exerciseDescription, String exerciseType, String exerciseDuration, String insulinType) {
        String sqlInsert = "INSERT INTO logData (userID, bloodSugar, notes, createTime, updateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        System.out.println("Inserting Data into logData table.");

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, bloodSugar);
            pstmt.setString(3, notes);
            pstmt.setString(4, createTime);
            pstmt.setString(5, updateTime);
            pstmt.setInt(6, logType);
            pstmt.setString(7, carb);
            pstmt.setString(8, mealDescription);
            pstmt.setString(9, insulinDose);
            pstmt.setString(10, medication);
            pstmt.setString(11, exerciseDescription);
            pstmt.setString(12, exerciseType);
            pstmt.setString(13, exerciseDuration);
            pstmt.setString(14, insulinType);

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteLog(int id, int userID) {
        System.out.println("Deleting record from logData table.");
        String sqlDelete = "DELETE FROM logData WHERE id = ? AND userID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setInt(1, id);
            pstmt.setInt(2, userID);
            pstmt.executeUpdate();
            System.out.println("Delete success.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLog(int logID, int userID, String bloodSugar, String notes, String createTime, String updateTime, int logType, String carb, String mealDescription, String insulinDose, String medication, String exerciseDescription, String exerciseType, String exerciseDuration, String insulinType) {
        System.out.println("Updating log in logData table.");
        String sqlUpdate = "UPDATE logData SET bloodSugar = ?, notes = ?, createTime = ?, updateTime = ?, logType = ?, carb = ?, mealDescription = ?, insulinDose = ?, medication = ?, exerciseDescription = ?, exerciseType = ?, exerciseDuration = ?, insulinType = ? WHERE id = ? AND userID = ?;";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setString(1, bloodSugar);
            pstmt.setString(2, notes);
            pstmt.setString(3, createTime);
            pstmt.setString(4, updateTime);
            pstmt.setInt(5, logType);
            pstmt.setString(6, carb);
            pstmt.setString(7, mealDescription);
            pstmt.setString(8, insulinDose);
            pstmt.setString(9, medication);
            pstmt.setString(10, exerciseDescription);
            pstmt.setString(11, exerciseType);
            pstmt.setString(12, exerciseDuration);
            pstmt.setString(13, insulinType);
            pstmt.setInt(14, logID);
            pstmt.setInt(15, userID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Log updated successfully.");
            } else {
                System.out.println("No log was updated. Check the log ID and User ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static JsonArray readAllLogs(int userID) {
        System.out.println("Reading data from logData table.");
        String sqlRead = "SELECT * FROM logData WHERE userID = ?";
        JsonArray logsArray = new JsonArray();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRead)) {

            pstmt.setInt(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Read SQL success.");
                while (rs.next()) {
                    JsonObject logJson = new JsonObject();
                    logJson.addProperty("id", rs.getInt("id"));
                    logJson.addProperty("userID", rs.getInt("userID"));
                    logJson.addProperty("bloodSugar", rs.getString("bloodSugar"));
                    logJson.addProperty("notes", rs.getString("notes"));
                    logJson.addProperty("createTime", rs.getString("createTime"));
                    logJson.addProperty("updateTime", rs.getString("updateTime"));
                    logJson.addProperty("logType", rs.getInt("logType"));
                    logJson.addProperty("carb", rs.getString("carb"));
                    logJson.addProperty("mealDescription", rs.getString("mealDescription"));
                    logJson.addProperty("insulinDose", rs.getString("insulinDose"));
                    logJson.addProperty("medication", rs.getString("medication"));
                    logJson.addProperty("exerciseDescription", rs.getString("exerciseDescription"));
                    logJson.addProperty("exerciseType", rs.getString("exerciseType"));
                    logJson.addProperty("exerciseDuration", rs.getString("exerciseDuration"));
                    logJson.addProperty("insulinType", rs.getString("insulinType"));

                    logsArray.add(logJson); // Add each log to the array
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logsArray;
    }


    public static void displayLogData(HttpServletResponse resp) {
        String sqlSelect = "SELECT * FROM logData;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            System.out.println("Display SQL success.");
            while (rs.next()) {
                int id = rs.getInt("id");
                int userID = rs.getInt("userID");
                String bloodSugar = rs.getString("bloodSugar");
                String notes = rs.getString("notes");
                String createTime = rs.getString("createTime");
                String updateTime = rs.getString("updateTime");
                int logType = rs.getInt("logType");
                String carb = rs.getString("carb");
                String mealDescription = rs.getString("mealDescription");
                String insulinDose = rs.getString("insulinDose");
                String medication = rs.getString("medication");
                String exerciseDescription = rs.getString("exerciseDescription");
                String exerciseType = rs.getString("exerciseType");
                String exerciseDuration = rs.getString("exerciseDuration");
                String insulinType = rs.getString("insulinType");

                resp.getWriter().write("ID: " + id + ", UserID: " + userID + ", BloodSugar: " + bloodSugar + ", Notes: " + notes + ", CreateTime: " + createTime + ", UpdateTime: " + updateTime + ", LogType: " + logType + ", Carb: " + carb + ", MealDescription: " + mealDescription + ", InsulinDose: " + insulinDose + ", Medication: " + medication + ", ExerciseDescription: " + exerciseDescription + ", ExerciseType: " + exerciseType + ", ExerciseDuration: " + exerciseDuration + ", InsulinType: " + insulinType + ". \n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
