package DiaLogServlet.DataBaseController.ControllerServlet.AddControl.AddUser;

import DiaLogApp.*;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserDataSQL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/login","/index","/","/index.html"}, loadOnStartup=1)
public class LoginServlet extends HttpServlet {

    public static int UserID;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserDataSQL.createTable();
        switch (servletPath) {
            case "/login":
                forwardTo(req, resp, "/login.html");
                break;
            case "/index.html":
                forwardTo(req, resp, "/login.html");
                break;
            case "/index":
                forwardTo(req, resp, "/login.html");
                break;
            case "/":
                forwardTo(req, resp, "/login.html");
                break;
            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/login":
                String jsonData1 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Gson gson1 = new Gson();
                UserData user1 = gson1.fromJson(jsonData1, UserData.class);

                String userAccountLogin = user1.getUserAccount();
                String userPasswordLogin = user1.getUserPassword();

                try {
                    UserID = UserDataSQL.checkIdentity(userAccountLogin,userPasswordLogin);
                    if (UserID != 0){
                        JsonObject jsonData= UserDataSQL.readUser(UserID);
                        sendResponse.send(resp, ErrorCode.SUCCESS, jsonData);
                    }
                    else {
                        sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

