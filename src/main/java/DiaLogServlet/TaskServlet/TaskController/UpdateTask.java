package DiaLogServlet.TaskServlet.TaskController;


import DiaLogServlet.TaskServlet.TaskData;
import DiaLogApp.GeneralMethod.BasicController.UpdateControl.Update;
import DiaLogServlet.TaskServlet.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;



@WebServlet(urlPatterns={"/api/post/update/task"}, loadOnStartup=1)

public class UpdateTask extends Update {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/update/task":
                update(req, resp);
        }
    }
    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        TaskData task = gson.fromJson(jsonData, TaskData.class);

        if (task.getUserId() != 0 && task.getId() != 0) {
            TaskDataSQL.updateTask(task); // Pass the entire TaskData object
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }

}
