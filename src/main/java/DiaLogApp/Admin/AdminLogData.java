/*

This page is for displaying all the LogData from all user for admin and testing purpose
with url: https://dialog-1d1125195912.herokuapp.com/LogDataTesting

 */

package DiaLogApp.Admin;

import DiaLogServlet.LogServlet.LogDataSQL;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/LogDataTesting"}, loadOnStartup=1)
public class AdminLogData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        LogDataSQL.createTable();
        switch (servletPath) {

            case "/LogDataTesting":
                resp.getWriter().write("LogData Display for testing purpose: \n");

                LogDataSQL.displayData(resp);
                JsonArray logsArray = LogDataSQL.readAllLogs(1);
                resp.getWriter().write(String.valueOf(logsArray));

                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
