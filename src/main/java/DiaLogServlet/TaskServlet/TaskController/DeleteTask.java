package DiaLogServlet.TaskServlet.TaskController;

import DiaLogServlet.TaskServlet.TaskData;
import DiaLogApp.GeneralMethod.BasicController.DeleteControl.Delete;
import DiaLogServlet.TaskServlet.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/api/post/delete/task"}, loadOnStartup=1)
public class DeleteTask extends Delete {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/delete/task":
                delete(req, resp);
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);

        int taskID = task.getId();
        int userID = task.getUserId();

        if (userID != 0 && taskID != 0){
            TaskDataSQL.deleteTask(taskID, userID);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        }
        else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }
}
