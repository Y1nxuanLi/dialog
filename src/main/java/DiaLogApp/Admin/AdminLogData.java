package DiaLogApp.Admin;

import DiaLogServlet.TaskServlet.TaskDataSQL;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/admin/LogData"}, loadOnStartup=1)
public class AdminLogData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        TaskDataSQL.createTable();
        switch (servletPath) {

            case "/admin/LogData":
                resp.getWriter().write("LogData Display for admin purpose: \n");
                TaskDataSQL.displayData(resp);
                JsonArray tasksArray = TaskDataSQL.readAllTask(15);
                resp.getWriter().write(String.valueOf(tasksArray));
                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

