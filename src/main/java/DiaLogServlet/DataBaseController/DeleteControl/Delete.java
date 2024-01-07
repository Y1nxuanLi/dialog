package DiaLogServlet.DataBaseController.DeleteControl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/api/post/delete"}, loadOnStartup=1)
public abstract class Delete extends HttpServlet implements Deleteable{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/api/post/delete":
                delete(req, resp);
        }
    }

    public abstract void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

