package DiaLogServlet.DataBaseController.ControllerServlet.ReadControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Read extends HttpServlet implements Readable {
    public abstract void read(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
