package DiaLogServlet;

import DiaLogApp.*;
import DiaLogServlet.Response.ErrorCode;
import DiaLogServlet.Response.ResponseObject;
import DiaLogSQL.UserDataSQL;
import DiaLogServlet.Response.sendResponse;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/home", "/register", "/UserDataTesting","/Admin"}, loadOnStartup=1)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserDataSQL.createTable();
        switch (servletPath) {
            case "/register":
                forwardTo(req, resp, "/register.html");
                break;
            case "/home":
                resp.getWriter().write("Welcome to the home page! ");
                break;
            case "/UserDataTesting":
                resp.getWriter().write("UserLoginData Display for testing purpose");
                UserDataSQL.displayData(resp);
                break;
            case "/Admin":
                UserDataSQL.insertData("Admin","1234567890");

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
            case "/register":
                String jsonData2 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Gson gson2 = new Gson();
                UserRegisterData user2 = gson2.fromJson(jsonData2, UserRegisterData.class);

                String userAccountRegister = user2.getUserAccount();
                String userPasswordRegister = user2.getUserPassword();
                String userConfirmedPassword = user2.getUserConfirmedPassword();

                try {
                    int UserID = UserDataSQL.checkIdentity(userAccountRegister,userAccountRegister);
                    if (UserID == 0){
                        UserDataSQL.insertData(userAccountRegister,userPasswordRegister);
                        sendResponse.send(resp, ErrorCode.SUCCESS);
                    }
                    else if (UserID != 0){
                        sendResponse.send(resp, ErrorCode.USER_EXIST);
                    }
                    else{
                        sendResponse.send(resp, ErrorCode.OPERATION_ERROR);
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

