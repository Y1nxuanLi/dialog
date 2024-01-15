/*

abstract Parent Class of AddLog AddTask implements Addable

 */

package DiaLogApp.GeneralMethod.BasicController.AddControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Add extends HttpServlet implements Addable {
    public abstract void add(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}