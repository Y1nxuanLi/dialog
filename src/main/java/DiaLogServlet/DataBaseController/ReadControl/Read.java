package DiaLogServlet.DataBaseController.ReadControl;

import DiaLogServlet.DataBaseController.DeleteControl.Deleteable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/api/post/read"}, loadOnStartup=1)
public abstract class Read extends HttpServlet implements Readable {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/api/post/read":
                read(req, resp);
        }

    }

    public abstract void read(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
