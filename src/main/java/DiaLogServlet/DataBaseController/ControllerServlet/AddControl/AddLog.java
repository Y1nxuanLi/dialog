package DiaLogServlet.DataBaseController.ControllerServlet.AddControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import DiaLogApp.LogData;
import DiaLogServlet.DataBaseController.SQLTableMethods.LogDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/api/post/add/log"}, loadOnStartup=1)
public class AddLog extends Add{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/add/log":
                add(req, resp);
        }
    }
    @Override
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        LogData log = gson.fromJson(jsonData, LogData.class);

        int userID = log.getUserId();
        String bloodSugar = log.getBloodSugar();
        String notes = log.getNotes();
        String createTime = log.getCreateTime();
        String updateTime = log.getUpdateTime();
        int logType = log.getLogType();
        String carb = log.getCarb();
        String mealDescription = log.getMealDescription();
        String insulinDose = log.getInsulinDose();
        String medication = log.getMedication();
        String exerciseDescription = log.getExerciseDescription();
        String exerciseType = log.getExerciseType();
        String exerciseDuration = log.getExerciseDuration();
        String insulinType = log.getInsulinType();

        if (userID != 0) {
            LogDataSQL.createTable();
            LogDataSQL.insertData(userID, bloodSugar, notes, createTime, updateTime, logType, carb, mealDescription, insulinDose, medication, exerciseDescription, exerciseType, exerciseDuration, insulinType);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }
}
