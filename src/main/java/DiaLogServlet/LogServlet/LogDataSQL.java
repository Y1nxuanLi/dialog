package DiaLogServlet.LogServlet;

import DiaLogApp.GeneralMethod.DatabaseConnector;
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

    public static void createTable() {

        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS logData (" +
                // Simple
                "id SERIAL PRIMARY KEY, " +
                "userId INT, " +
                "bloodSugar VARCHAR(128), " +

                "notes VARCHAR(128), " +
                "createTime VARCHAR(128), " +
                "updateTime VARCHAR(128), " +
                "logType VARCHAR(128), " +

                //Comprehensive
                "carb VARCHAR(128), " +
                "mealDescription VARCHAR(128), " +
                "insulinDose VARCHAR(128), " +
                "medication VARCHAR(128), " +
                "exerciseDescription VARCHAR(128), " +

                //Intensive
                "exerciseType VARCHAR(128), " +
                "exerciseDuration VARCHAR(128), " +
                "insulinType VARCHAR(128)); ";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("taskData Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(LogData logData) {
        String sqlInsert = "INSERT INTO logData (" +
                "userId, bloodSugar, notes, createTime, updateTime, logType, carb, " +
                "mealDescription, insulinDose, medication, exerciseDescription, " +
                "exerciseType, exerciseDuration, insulinType) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        System.out.println("Inserting Data into logData table.");

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setInt(1, logData.getUserId());
            pstmt.setString(2, logData.getBloodSugar());
            pstmt.setString(3, logData.getNotes());
            pstmt.setString(4, logData.getCreateTime());
            pstmt.setString(5, logData.getUpdateTime());
            pstmt.setString(6, logData.getLogType());
            pstmt.setString(7, logData.getCarb());
            pstmt.setString(8, logData.getMealDescription());
            pstmt.setString(9, logData.getInsulinDose());
            pstmt.setString(10, logData.getMedication());
            pstmt.setString(11, logData.getExerciseDescription());
            pstmt.setString(12, logData.getExerciseType());
            pstmt.setString(13, logData.getExerciseDuration());
            pstmt.setString(14, logData.getInsulinType());

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLog(LogData log) {
        System.out.println("Updating log in logData table.");
        String sqlUpdate = "UPDATE logData SET bloodSugar = ?, notes = ?, createTime = ?, " +
                "updateTime = ?, logType = ?, carb = ?, mealDescription = ?, insulinDose = ?, " +
                "medication = ?, exerciseDescription = ?, exerciseType = ?, exerciseDuration = ?, " +
                "insulinType = ? WHERE id = ? AND userId = ?;";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setString(1, log.getBloodSugar());
            pstmt.setString(2, log.getNotes());
            pstmt.setString(3, log.getCreateTime());
            pstmt.setString(4, log.getUpdateTime());
            pstmt.setString(5, log.getLogType());
            pstmt.setString(6, log.getCarb());
            pstmt.setString(7, log.getMealDescription());
            pstmt.setString(8, log.getInsulinDose());
            pstmt.setString(9, log.getMedication());
            pstmt.setString(10, log.getExerciseDescription());
            pstmt.setString(11, log.getExerciseType());
            pstmt.setString(12, log.getExerciseDuration());
            pstmt.setString(13, log.getInsulinType());
            pstmt.setInt(14, log.getId());
            pstmt.setInt(15, log.getUserId());

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


    public static JsonArray readAllLogs(int userId) {
        System.out.println("Reading data from logData table.");
        String sqlRead = "SELECT * FROM logData WHERE userId = ?";
        JsonArray logsArray = new JsonArray();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRead)) {

            pstmt.setInt(1, userId);
            System.out.println("Executing query: " + pstmt);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Read SQL success.");
                while (rs.next()) {
                    JsonObject logJson = new JsonObject();
                    System.out.println("Log found: " + rs.getInt("id"));

                    logJson.addProperty("id", rs.getInt("id"));
                    logJson.addProperty("userId", rs.getInt("userId"));
                    logJson.addProperty("bloodSugar", rs.getString("bloodSugar"));
                    logJson.addProperty("notes", rs.getString("notes"));
                    logJson.addProperty("createTime", rs.getString("createTime"));
                    logJson.addProperty("updateTime", rs.getString("updateTime"));
                    logJson.addProperty("logType", rs.getString("logType"));
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
                System.out.println("Query executed. Number of logs found: " + logsArray.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logsArray;
    }
    public static void deleteLog(LogData log) {
        System.out.println("Deleting record from logData table.");
        String sqlDelete = "DELETE FROM logData WHERE id = ? AND userId = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setInt(1, log.getId());
            pstmt.setInt(2, log.getUserId());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Delete success.");
            } else {
                System.out.println("No record was deleted. Check the log ID and User ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayData(HttpServletResponse resp) {
        String sqlSelect = "SELECT * FROM logData;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            System.out.println("Display SQL success.");
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String bloodSugar = rs.getString("bloodSugar");
                String notes = rs.getString("notes");
                String createTime = rs.getString("createTime");
                String updateTime = rs.getString("updateTime");
                String logType = rs.getString("logType");
                String carb = rs.getString("carb");
                String mealDescription = rs.getString("mealDescription");
                String insulinDose = rs.getString("insulinDose");
                String medication = rs.getString("medication");
                String exerciseDescription = rs.getString("exerciseDescription");
                String exerciseType = rs.getString("exerciseType");
                String exerciseDuration = rs.getString("exerciseDuration");
                String insulinType = rs.getString("insulinType");

                resp.getWriter().write("ID: " + id + ", UserId: " + userId + ", BloodSugar: " + bloodSugar + ", Notes: " + notes + ", CreateTime: " + createTime + ", UpdateTime: " + updateTime + ", LogType: " + logType + ", Carb: " + carb + ", MealDescription: " + mealDescription + ", InsulinDose: " + insulinDose + ", Medication: " + medication + ", ExerciseDescription: " + exerciseDescription + ", ExerciseType: " + exerciseType + ", ExerciseDuration: " + exerciseDuration + ", InsulinType: " + insulinType + ". \n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
