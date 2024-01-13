package DiaLogServlet.Admin;

import DiaLogServlet.DataBaseController.SQLTableMethods.LogDataSQL;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/LogDataTesting"}, loadOnStartup=1)
public class TestServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        LogDataSQL.createTable();
        switch (servletPath) {

            case "/LogDataTesting":
                resp.getWriter().write("LogData Display for testing purpose: \n");

                // Test data for insertData method
                int userID = 15;
                String bloodSugar = "120 mg/dL";
                String notes = "Felt dizzy in the morning";
                String createTime = "2024-01-13 08:00:00";
                String updateTime = "2024-01-13 09:00:00";
                int logType = 1;
                String carb = "45g";
                String mealDescription = "Breakfast - Oatmeal and fruits";
                String insulinDose = "10 units";
                String medication = "Metformin";
                String exerciseDescription = "Morning walk";
                String exerciseType = "Walking";
                String exerciseDuration = "30 minutes";
                String insulinType = "Rapid-acting";

                LogDataSQL.insertData(userID, bloodSugar, notes, createTime, updateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType);


                int logID = 1;
                String updatedBloodSugar = "110 mg/dL";
                String updatedNotes = "Feeling better";
                String updatedUpdateTime = "2024-01-13 10:00:00";


                LogDataSQL.updateLog(logID, userID, updatedBloodSugar, updatedNotes, createTime, updatedUpdateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType);
                LogDataSQL.displayData(resp);
                JsonArray tasksArray = LogDataSQL.readAllLogs(15);
                resp.getWriter().write(String.valueOf(tasksArray));

                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }


}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

