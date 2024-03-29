/*

API servlet for https://dialog-1d1125195912.herokuapp.com/api/post/add/task
handling doPost request

Get current userID from client
insert data received from client to TaskDataSQL table
Response message with no data

 */

package DiaLogServlet.TaskServlet.TaskController;

import DiaLogServlet.TaskServlet.TaskData;
import DiaLogApp.GeneralMethod.BasicController.AddControl.Add;
import DiaLogServlet.TaskServlet.TaskDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


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
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        TaskData task = gson.fromJson(jsonData, TaskData.class);

        if (task.getUserId() != 0) {
            TaskDataSQL.createTable();
            TaskDataSQL.insertData(task); // Pass the entire TaskData object
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }

}