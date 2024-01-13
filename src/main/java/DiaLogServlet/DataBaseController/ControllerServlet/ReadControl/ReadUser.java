package DiaLogServlet.DataBaseController.ControllerServlet.ReadControl;

import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserDataSQL;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DiaLogServlet.DataBaseController.ControllerServlet.AddControl.AddUser.LoginServlet.UserID;


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