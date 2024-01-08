package DiaLogServlet.DataBaseController.ControllerServlet.DeleteControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Deleteable {
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
