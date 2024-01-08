package DiaLogServlet;

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


@WebServlet(urlPatterns={"/home", "/admin"}, loadOnStartup=1)
public class TestServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {

            case "/home":
                resp.getWriter().write("Welcome to the home page! ");

                int testUserID = 1; // Example user ID
                String testTitle = "Test Task";
                String testContent = "This is a test task content.";
                LocalDateTime testCreateTime = LocalDateTime.now();
                LocalDateTime testUpdateTime = null; // Assuming update time can be null
                LocalDateTime testDueTime = LocalDateTime.now().plusDays(7); // Example due date, 7 days from now
                int testNotification = 1;
                break;

            case "/admin":
                resp.getWriter().write("Add admin data");
                try {
                    int UserID = UserLoginDataSQL.checkIdentity("Admin","1234567890");
                    if (UserID == 0){
                        UserLoginDataSQL.insertData("Admin","1234567890");
                        sendResponse.send(resp, ErrorCode.SUCCESS);
                    }
                    else{
                        sendResponse.send(resp, ErrorCode.USER_EXIST);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

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

