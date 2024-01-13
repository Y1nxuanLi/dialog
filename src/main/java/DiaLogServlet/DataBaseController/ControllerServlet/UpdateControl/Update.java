package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class Update extends HttpServlet implements Updateable {
    public abstract void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException;
}
