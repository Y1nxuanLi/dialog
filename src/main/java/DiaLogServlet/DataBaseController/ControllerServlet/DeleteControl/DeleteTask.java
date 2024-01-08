package DiaLogServlet.DataBaseController.ControllerServlet.DeleteControl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/api/post/delete/task"}, loadOnStartup=1)
public class DeleteTask extends Delete{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/delete/task":
                delete(req, resp);
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {


    }
}
