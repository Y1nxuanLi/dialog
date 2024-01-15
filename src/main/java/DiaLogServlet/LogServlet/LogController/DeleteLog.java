/*

API servlet for https://dialog-1d1125195912.herokuapp.com/api/post/delete/log
handling doPost request

Get current userID and logID from client
delete the selected log in LogDataSQL table
Response message with no data

 */

package DiaLogServlet.LogServlet.LogController;

import DiaLogServlet.LogServlet.LogData;
import DiaLogApp.GeneralMethod.BasicController.DeleteControl.Delete;
import DiaLogServlet.LogServlet.LogDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/api/post/delete/log"}, loadOnStartup=1)
public class DeleteLog extends Delete {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/delete/log":
                delete(req, resp);
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        LogData log = gson.fromJson(jsonData, LogData.class);

        if (log.getUserId() != 0 && log.getId() != 0) {
            LogDataSQL.deleteLog(log); // Pass the entire LogData object
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }

}

