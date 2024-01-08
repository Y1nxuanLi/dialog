package DiaLogServlet;

import DiaLogServlet.DataBaseController.SQLTableMethods.UserLoginDataSQL;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/home", "/UserDataTesting","/admin"}, loadOnStartup=1)
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {

            case "/home":
                resp.getWriter().write("Welcome to the home page! ");
                int UserID = 5;
                JsonObject jsonData= UserLoginDataSQL.readUser(UserID);
                sendResponse.send(resp, ErrorCode.SUCCESS, jsonData);
                break;
            case "/UserDataTesting":
                resp.getWriter().write("UserLoginData Display for testing purpose: \n");
                UserLoginDataSQL.displayData(resp);
                break;
            case "/admin":
                resp.getWriter().write("Add admin data");
                UserLoginDataSQL.insertData("Admin","1234567890");

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

