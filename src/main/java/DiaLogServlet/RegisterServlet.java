package DiaLogServlet;

import DiaLogApp.*;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserLoginDataSQL;
import DiaLogServlet.ServletResponse.sendResponse;
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


@WebServlet(urlPatterns={"/register"}, loadOnStartup=1)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserLoginDataSQL.createTable();
        switch (servletPath) {
            case "/register":
                forwardTo(req, resp, "/register.html");
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
            case "/register":
                String jsonData2 = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Gson gson2 = new Gson();
                UserRegisterData user2 = gson2.fromJson(jsonData2, UserRegisterData.class);

                String userAccountRegister = user2.getUserAccount();
                String userPasswordRegister = user2.getUserPassword();
                String userConfirmedPassword = user2.getUserConfirmedPassword();

                try {
                    int UserID = UserLoginDataSQL.checkIdentity(userAccountRegister,userAccountRegister);
                    if (UserID == 0){
                        UserLoginDataSQL.insertData(userAccountRegister,userPasswordRegister);
                        sendResponse.send(resp, ErrorCode.SUCCESS);
                    }
                    else{
                        sendResponse.send(resp, ErrorCode.USER_EXIST);
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

