package DiaLogServlet.Admin;

import DiaLogServlet.DataBaseController.SQLTableMethods.LogDataSQL;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserLoginDataSQL;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/LogDataTesting"}, loadOnStartup=1)
public class TestServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {

            case "/LogDataTesting":
                resp.getWriter().write("LogData Display for testing purpose: \n");
                LogDataSQL.displayData(resp);
                JsonArray tasksArray = LogDataSQL.readAllLogs(17);
                resp.getWriter().write(String.valueOf(tasksArray));
//                LogDataSQL.updateLog(20, 16, "title", "content", "createTime", "updateTime", "dueTime", 0);
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

