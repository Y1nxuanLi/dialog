package DiaLogServlet.DataBaseController.ControllerServlet.ReadControl;
import DiaLogApp.LogData;
import DiaLogServlet.DataBaseController.SQLTableMethods.LogDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/api/post/read/log"}, loadOnStartup=1)
public class ReadLog extends Read {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/read/log":
                read(req, resp);
        }
    }
    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        LogData log = gson.fromJson(jsonData, LogData.class);

        int logID = log.getId();
        int userID = log.getUserId();

        if (userID != 0 && logID != 0) {
            JsonArray jsonDataArray = LogDataSQL.readAllLogs(userID); // Assuming readAllLogs method returns JsonArray
            sendResponse.send(resp, ErrorCode.SUCCESS, jsonDataArray);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }

}

