package DiaLogServlet.DataBaseController.ControllerServlet.AddControl;


import DiaLogApp.TaskData;
import DiaLogApp.UserLoginData;
import DiaLogServlet.DataBaseController.SQLTableMethods.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static DiaLogServlet.LoginServlet.UserID;

@WebServlet(urlPatterns={"/api/post/add/task"}, loadOnStartup=1)
public class AddTask extends Add {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/add/task":
                add(req, resp);
        }
    }


    @Override
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);

        int userID = task.getUserID();
        String title = task.getTitle();
        String content = task.getContent();
        LocalDateTime createTime = task.getCreateTime();
        LocalDateTime updateTime = task.getUpdateTime();
        LocalDateTime dueTime = task.getDueTime();
        int notification = task.getNotification();
        if (UserID != 0){
            TaskDataSQL.createTable();
            TaskDataSQL.insertData(userID, title, content, createTime, updateTime, dueTime, notification);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        }
        else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }

}