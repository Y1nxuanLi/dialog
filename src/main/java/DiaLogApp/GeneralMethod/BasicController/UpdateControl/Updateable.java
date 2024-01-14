package DiaLogApp.GeneralMethod.BasicController.UpdateControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Updateable {
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException;
}
