/*

abstract Parent Class of DeleteUser DeleteLog DeleteTask implements Deleteable

 */

package DiaLogApp.GeneralMethod.BasicController.DeleteControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Delete extends HttpServlet implements Deleteable{
    public abstract void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

