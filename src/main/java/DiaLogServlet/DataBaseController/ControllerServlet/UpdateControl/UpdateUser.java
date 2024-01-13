package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;


import DiaLogApp.TaskData;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/api/post/update/user"}, loadOnStartup=1)
public class UpdateUser extends Update{
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson1 = new Gson();
        TaskData task = gson1.fromJson(jsonData1, TaskData.class);
//
//        int taskID = task.getId();
//        int userID = task.getUserId();
//        String title = task.getTitle();
//        String content = task.getContent();
//        String createTime = task.getCreateTime();
//        String updateTime = task.getUpdateTime();
//        String dueTime = task.getDueTime();
//        int notification = task.getNotification();
//
//        if (userID != 0 && taskID !=0) {
//            TaskDataSQL.updateTask(taskID, userID, title, content, createTime, updateTime, dueTime, notification);
//            sendResponse.send(resp, ErrorCode.SUCCESS);
//        } else {
//            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
//        }


    }
}
