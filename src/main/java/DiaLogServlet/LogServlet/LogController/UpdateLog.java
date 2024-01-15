/*

API servlet for https://dialog-1d1125195912.herokuapp.com/api/post/update/log
handling doPost request

Get current userID and logID from client
updated the LogDataSQL table with new entry
Response message with no data


 */

package DiaLogServlet.LogServlet.LogController;

import DiaLogServlet.LogServlet.LogData;
import DiaLogApp.GeneralMethod.BasicController.UpdateControl.Update;
import DiaLogServlet.LogServlet.LogDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/api/post/update/log"}, loadOnStartup=1)

public class UpdateLog extends Update {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/update/log":
                update(req, resp);
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        LogData log = gson.fromJson(jsonData, LogData.class);

        if (log.getUserId() != 0 && log.getId() != 0) {
            LogDataSQL.updateLog(log);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }
}