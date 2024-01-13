package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;


import DiaLogApp.TaskData;
import DiaLogServlet.DataBaseController.SQLTableMethods.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;



@WebServlet(urlPatterns={"/api/post/update/task"}, loadOnStartup=1)

public class UpdateTask extends Update{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/update/task":
                update(req, resp);
        }
    }
    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);

        int taskID = task.getId();
        int userID = task.getUserId();
        String title = task.getTitle();
        String content = task.getContent();
        String createTime = task.getCreateTime();
        String updateTime = task.getUpdateTime();
        String dueTime = task.getDueTime();
        int notification = task.getNotification();

        if (userID != 0 && taskID !=0) {
            TaskDataSQL.updateTask(taskID, userID, title, content, createTime, updateTime, dueTime, notification);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }
}
