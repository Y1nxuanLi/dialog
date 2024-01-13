package DiaLogServlet.Admin;

import DiaLogServlet.DataBaseController.SQLTableMethods.UserLoginDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;


@WebServlet(urlPatterns={"/UserDataTesting"}, loadOnStartup=1)
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {

            case "/UserDataTesting":
                resp.getWriter().write("UserLoginData Display for testing purpose: \n");
                UserLoginDataSQL.displayUserData(resp);
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

