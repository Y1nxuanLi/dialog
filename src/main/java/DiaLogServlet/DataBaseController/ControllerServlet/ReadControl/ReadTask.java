package DiaLogServlet.DataBaseController.ControllerServlet.ReadControl;
import DiaLogApp.TaskData;
import DiaLogServlet.DataBaseController.SQLTableMethods.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/api/post/read/task"}, loadOnStartup=1)
public class ReadTask extends Read {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/read/task":
                read(req, resp);
        }
    }
    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);

        int taskID = task.getId();
        int userID = task.getUserId();

        if (userID != 0 && taskID !=0) {
            JsonObject jsonData= TaskDataSQL.readTask(taskID, userID);
            sendResponse.send(resp, ErrorCode.SUCCESS, jsonData);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }
}

