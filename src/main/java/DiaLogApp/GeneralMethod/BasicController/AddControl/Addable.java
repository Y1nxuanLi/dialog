package DiaLogApp.GeneralMethod.BasicController.AddControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public interface Addable {
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

