package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;


import DiaLogServlet.DataBaseController.ControllerServlet.ReadControl.Read;
import DiaLogServlet.ServletResponse.ErrorCode;
import DiaLogServlet.DataBaseController.SQLTableMethods.UserLoginDataSQL;
import DiaLogServlet.ServletResponse.sendResponse;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DiaLogServlet.LoginServlet.UserID;


@WebServlet(urlPatterns={"/api/post/update/user"}, loadOnStartup=1)
public class UpdateUser extends Update{

}
