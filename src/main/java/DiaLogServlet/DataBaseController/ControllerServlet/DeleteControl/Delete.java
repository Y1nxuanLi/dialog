package DiaLogServlet.DataBaseController.ControllerServlet.DeleteControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class Delete extends HttpServlet implements Deleteable{
    public abstract void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

