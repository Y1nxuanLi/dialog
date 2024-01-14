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

//                // Test data for insertData method
//                int userID = 1;
//                String bloodSugar = "12";
//                String notes = "";
//                String createTime = "2024-01-03 08:00:00";
//                String updateTime = "";
//                String logType = "2";
//                String carb = "45";
//                String mealDescription = "Breakfast";
//                String insulinDose = "";
//                String medication = "";
//                String exerciseDescription = "";
//                String exerciseType = "";
//                String exerciseDuration = "";
//                String insulinType = "";
//
//                LogDataSQL.insertData(userID, bloodSugar, notes, createTime, updateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType);
//
//
//                int logID = 1;
//                String updatedBloodSugar = "5";
//                String updatedNotes = "Feeling better";
//                String updatedUpdateTime = "2024-01-13 19:00:00";
//
//
//                LogDataSQL.updateLog(logID, userID, updatedBloodSugar, updatedNotes, createTime, updatedUpdateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType);

                LogDataSQL.displayData(resp);
                JsonArray logsArray = LogDataSQL.readAllLogs(1);
                resp.getWriter().write(String.valueOf(logsArray));

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

//{"userId":1,"bloodSugar":"45","notes":"","createTime":"","updateTime":"","logType":"","carb":"","mealDescription":"","insulinDose":"","medication":"","exerciseDescription":"","exerciseType":"","exerciseDuration":"","insulinType":""}
//
//{"code":200,"message":"SUCCESS","data":[{"id":2,"userId":1,"bloodSugar":"12","notes":"","createTime":"2024-01-03 08:00:00","updateTime":"","logType":2,"carb":"45","mealDescription":"Breakfast","insulinDose":"","medication":"","exerciseDescription":"","exerciseType":"","exerciseDuration":"","insulinType":""},{"id":1,"userId":1,"bloodSugar":"5","notes":"Feeling better","createTime":"2024-01-03 08:00:00","updateTime":"2024-01-13 19:00:00","logType":2,"carb":"45","mealDescription":"Breakfast","insulinDose":"","medication":"","exerciseDescription":"","exerciseType":"","exerciseDuration":"","insulinType":""}]}
