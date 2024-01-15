/*

This page is for displaying all the TaskData from all user for admin and testing purpose
with url: https://dialog-1d1125195912.herokuapp.com/TaskDataTesting

 */

package DiaLogApp.Admin;

import DiaLogServlet.TaskServlet.TaskDataSQL;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/TaskDataTesting"}, loadOnStartup=1)
public class AdminTaskData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        TaskDataSQL.createTable();
        switch (servletPath) {

            case "/TaskDataTesting":
                resp.getWriter().write("TaskLoginData Display for testing purpose: \n");
                TaskDataSQL.displayData(resp);
                JsonArray tasksArray = TaskDataSQL.readAllTask(1);
                resp.getWriter().write(String.valueOf(tasksArray));

                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
