package DiaLogServlet.DataBaseController.ReadControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Readable {
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
