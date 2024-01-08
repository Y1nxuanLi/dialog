package DiaLogServlet.DataBaseController.SQLTableMethods;

import DiaLogServlet.DataBaseController.DatabaseConnector;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;
public class TaskDataSQL {
    private Long id;
    private String content;
    private String title;
    private Long userId;
    private String createTime;
    private String updateTime;
    public static void createTable() {

        System.out.println("Creating table.");
        String sqlCreate = "CREATE TABLE IF NOT EXISTS taskData (" +
                "id SERIAL PRIMARY KEY, " +
                "userID INT NOT NULL, " +
                "title VARCHAR(128), " +
                "content VARCHAR(128), " +
                "createTime VARCHAR(128) NOT NULL, " +
                "updateTime VARCHAR(128), " +
                "dueTime VARCHAR(128), " +
                "notification INT;";


        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreate);
            System.out.println("taskData Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    LocalDateTime currentDateTime = LocalDateTime.now();
    public static void insertData(int userID, String title, String content, String createTime, String updateTime, String dueTime, int notification) {
        String sqlInsert = "INSERT INTO taskData (userID, title, content, createTime, updateTime, dueTime, notification) VALUES (?,?,?,?,?,?,?);";
        System.out.println("Inserting Data table.");
        System.out.println("Inserting Data into taskData table.");

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, title);
            pstmt.setString(3, content);
            pstmt.setString(4, createTime);
            pstmt.setString(5, updateTime);
            pstmt.setString(6, dueTime);
            pstmt.setInt(7, notification);

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayData(HttpServletResponse resp) {
        String sqlSelect = "SELECT * FROM taskData;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            System.out.println("Display SQL success.");
            while (rs.next()) {
                int id = rs.getInt("id");
                String userID = rs.getString("userID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createTime = rs.getString("createTime");
                String updateTime = rs.getString("updateTime");
                String dueTime = rs.getString("dueTime");
                String notification = rs.getString("notification");

                resp.getWriter().write("ID: " + id + ", UserID: " + userID + ", Title: " + title + ", Content: " + content + ", CreateTime: " + createTime + ", UpdateTime: " + updateTime + ", DueTime: " + dueTime + ", Notification: " + notification + ". ");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void deleteTask(int id, int userID) {
        System.out.println("Deleting record from taskData table.");
        String sqlDelete = "DELETE FROM taskData WHERE id = ? AND userID = ?";

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


    public static JsonObject readTask(int id, int userID) {
        System.out.println("Reading data from userLoginData table.");
        String sqlRead = "SELECT * FROM userLoginData WHERE id = ? AND userID = ?";
        JsonObject jsonData = new JsonObject();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRead)) {

            pstmt.setInt(1, id);
            pstmt.setInt(2, userID);

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                System.out.println("Read SQL success.");
                if (rs.next()) { // Use if instead of while if you're expecting only one result
                    jsonData.addProperty("id", rs.getInt("id"));
                    jsonData.addProperty("userID", rs.getString("userID"));
                    jsonData.addProperty("title", rs.getString("title"));
                    jsonData.addProperty("content", rs.getString("content"));
                    jsonData.addProperty("createTime", rs.getString("createTime").toString());
                    jsonData.addProperty("updateTime", rs.getString("updateTime") != null ? rs.getString("updateTime").toString() : null);
                    jsonData.addProperty("dueTime", rs.getString("dueTime") != null ? rs.getString("dueTime").toString() : null);
                    jsonData.addProperty("notification", rs.getString("notification"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonData;
    }

    public static JsonArray readAllTask(int userID) {
        System.out.println("Reading data from userLoginData table.");
        String sqlRead = "SELECT * FROM userLoginData WHERE userID = ?";
        JsonArray tasksArray = new JsonArray();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRead)) {

            pstmt.setInt(1, userID); // Set the userID parameter here, before executing the query

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                System.out.println("Read SQL success.");
                while (rs.next()) { // Use while loop to iterate through all results
                    JsonObject taskJson = new JsonObject();
                    taskJson.addProperty("id", rs.getInt("id"));
                    taskJson.addProperty("userID", rs.getInt("userID"));
                    taskJson.addProperty("title", rs.getString("title"));
                    taskJson.addProperty("content", rs.getString("content"));
                    taskJson.addProperty("createTime", rs.getString("createTime"));
                    taskJson.addProperty("updateTime", rs.getString("updateTime") != null ? rs.getString("updateTime") : null);
                    taskJson.addProperty("dueTime", rs.getString("dueTime") != null ? rs.getString("dueTime") : null);
                    taskJson.addProperty("notification", rs.getInt("notification"));

                    tasksArray.add(taskJson); // Add each task to the array
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasksArray;
    }

}

