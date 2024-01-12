package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;


import DiaLogServlet.DataBaseController.ControllerServlet.ReadControl.Readable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Update extends HttpServlet implements Updateable {
    public abstract void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
