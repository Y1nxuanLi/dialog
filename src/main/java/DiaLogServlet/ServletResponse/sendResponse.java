package DiaLogServlet.ServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sendResponse {
    public static void send(HttpServletResponse resp, ErrorCode errorCode) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(errorCode.getCode(), errorCode.getMessage())));
    }

    public static void send(HttpServletResponse resp, ErrorCode errorCode, int id) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(errorCode.getCode(), errorCode.getMessage(), id)));
    }

    public static void send(HttpServletResponse resp, ErrorCode errorCode, JsonObject jsonData) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(new ResponseObject(errorCode.getCode(), errorCode.getMessage(), jsonData)));
    }
}
