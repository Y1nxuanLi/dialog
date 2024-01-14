package DiaLogServlet.UserServlet.UserController;

import DiaLogServlet.UserServlet.UserDataSQL;
import DiaLogApp.GeneralMethod.BasicController.ReadControl.Read;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DiaLogServlet.UserServlet.UserController.AddUser.LoginServlet.UserID;


@WebServlet(urlPatterns={"/api/post/read/user"}, loadOnStartup=1)
public class ReadUser extends Read {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/read/user":
                read(req, resp);
        }
    }

    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (UserID != 0) {
            JsonObject jsonData= UserDataSQL.readUser(UserID);
            sendResponse.send(resp, ErrorCode.SUCCESS, jsonData);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }

    }

}