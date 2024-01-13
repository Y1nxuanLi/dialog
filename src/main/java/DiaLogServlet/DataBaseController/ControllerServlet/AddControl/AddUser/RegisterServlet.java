package DiaLogServlet.DataBaseController.ControllerServlet.AddControl.AddUser;

import DiaLogApp.*;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserDataSQL;
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
import java.util.Objects;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/register"}, loadOnStartup=1)
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
                String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Gson gson = new Gson();
                UserData user = gson.fromJson(jsonData, UserData.class);

                String userAccountRegister = user.getUserAccount();
                String userPasswordRegister = user.getUserPassword();
                String userConfirmedPassword = user.getUserConfirmedPassword();

                try {
                    int UserID = UserDataSQL.checkIdentity(userAccountRegister,userAccountRegister);
                    if (UserID == 0 && Objects.equals(userPasswordRegister, userConfirmedPassword)){
                        UserDataSQL.insertData(user);
                        sendResponse.send(resp, ErrorCode.SUCCESS);
                    }
                    else if(UserID == 0 && !Objects.equals(userPasswordRegister, userConfirmedPassword)){
                        sendResponse.send(resp, ErrorCode.DIFFERENT_PASSWORD);
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


