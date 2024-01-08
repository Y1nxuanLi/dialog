package DiaLogServlet.DataBaseController.ControllerServlet.ReadControl;

import DiaLogApp.TaskData;
import DiaLogServlet.DataBaseController.SQLTableMethods.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static DiaLogServlet.LoginServlet.UserID;

@WebServlet(urlPatterns={"/api/post/read/all"}, loadOnStartup=1)
public class ReadAll extends Read {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/read/all":
                read(req, resp);
        }
    }
    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);

        int userID = task.getUserID();

        if (userID != 0) {
            JsonArray jsonData= TaskDataSQL.readAllTask(userID);
            sendResponse.send(resp, ErrorCode.SUCCESS, jsonData);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }
}

