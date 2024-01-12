package DiaLogServlet.DataBaseController.ControllerServlet.UpdateControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Updateable {
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
