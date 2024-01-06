package DiaLogServlet;

import DiaLogApp.*;
import DiaLogSQL.UserLoginDataSQL;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/login"}, loadOnStartup=1)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {
            case "/login":
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
                UserLoginData user1 = gson1.fromJson(jsonData1, UserLoginData.class);

                String userAccountLogin = user1.getUserAccount();
                String userPasswordLogin = user1.getUserPassword();

                try {
                    int UserID = UserLoginDataSQL.checkIdentity(userAccountLogin,userPasswordLogin);
                    if (UserID != 0){
                        sendSuccessResponse(resp);
                    }
                    else {
                        sendErrorResponse(resp);
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

    private void sendSuccessResponse(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage())));

    }
    private void sendErrorResponse(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(ErrorCode.NO_AUTH_ERROR.getCode(), ErrorCode.NO_AUTH_ERROR.getMessage())));
    }
}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

