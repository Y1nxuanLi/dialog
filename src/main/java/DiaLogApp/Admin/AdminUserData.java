/*

This page is for displaying all the UserData from all user for admin and testing purpose
with url: https://dialog-1d1125195912.herokuapp.com/UserDataTesting

 */

package DiaLogApp.Admin;

import DiaLogServlet.UserServlet.UserDataSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DiaLogServlet.UserServlet.UserController.AddUser.LoginServlet.UserID;


@WebServlet(urlPatterns={"/UserDataTesting"}, loadOnStartup=1)
public class AdminUserData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserDataSQL.createTable();
        switch (servletPath) {

            case "/UserDataTesting":

                UserDataSQL.displayUserData(resp);

                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}
