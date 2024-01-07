package DiaLogServlet.DataBaseController.ReadControl;

import DiaLogServlet.Response.ErrorCode;
import DiaLogSQL.UserDataSQL;
import DiaLogServlet.Response.sendResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DiaLogServlet.LoginServlet.UserID;


@WebServlet(urlPatterns={"/api/post/read/all"}, loadOnStartup=1)
public class ReadAll extends Read {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/read/all":
                read(req, resp);
        }
    }

    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (UserID == 0) {
            sendResponse.send(resp, ErrorCode.DATA_NOT_FOUND_ERROR);
        }
        if (UserID != 0) {
            UserDataSQL.deleteUser(UserID);
            sendResponse.send(resp, ErrorCode.SUCCESS);
        } else {
            sendResponse.send(resp, ErrorCode.OPERATION_ERROR);
        }

    }
}