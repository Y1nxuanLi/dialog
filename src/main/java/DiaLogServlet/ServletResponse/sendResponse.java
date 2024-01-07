package DiaLogServlet.ServletResponse;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sendResponse {
    public static void send(HttpServletResponse resp, ErrorCode errorCode) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(errorCode.getCode(), errorCode.getMessage())));
    }
}
