package DiaLogServlet.UserServlet.UserController;


import DiaLogServlet.UserServlet.UserData;
import DiaLogServlet.UserServlet.UserDataSQL;
import DiaLogApp.GeneralMethod.BasicController.UpdateControl.Update;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/api/post/update/user"}, loadOnStartup=1)
public class UpdateUser extends Update {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/update/user":
                try {
                    update(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        UserData userData = gson.fromJson(jsonData, UserData.class);

        if (userData != null) {
            UserDataSQL.updateUser(userData);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
    }
}
