package DiaLogServlet;

import DiaLogApp.Patient;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/home", "/patients", "/doctors", "/register", "/login"}, loadOnStartup=1)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
//        resp.getWriter().write("Welcome to DiaLog ");
        String servletPath = req.getServletPath();
        // You can now use the servletPath in your code
//        resp.getWriter().println("Servlet Path: " + servletPath);

        switch (servletPath) {
            case "/login":
//                resp.getWriter().write("Register Page");
                forwardTo(req, resp, "/login.html");
                break;
            case "/register":
//                resp.getWriter().write("Register Page");
                forwardTo(req, resp, "/register.html");
                break;
            case "/doctors":
                resp.getWriter().write("Hello, doctor! ");
                break;
            case "/patients":
                resp.getWriter().write("Hello, patient! ");
                break;
            case "/home": // default case is for root path ("/")
                resp.getWriter().write("Welcome to the home page! ");
                break;
            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        // req is sent from user to server
        // resp is sent from server to user

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        Patient patient1 = gson.fromJson(requestBody, Patient.class);

        // Responding based on the request path
        String servletPath = req.getServletPath();
        String responseMessage = "Received at " + req.getServletPath() + ": " + requestBody;
        if (Objects.equals(servletPath, "/patients")) {
            // Process the request for the '/patients' endpoint
            resp.setContentType("text/plain");
            // resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Received at /patients: " + requestBody);
        } else if (Objects.equals(servletPath, "/doctors")) {
            // Process the request for the '/doctors' endpoint, if needed
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Received at /doctors: " + requestBody);
        }
        System.out.println("Sent response: " + responseMessage);
        System.out.println(patient1.getName());

    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

}
// College IP: 146.169.219.59
// http://146.169.219.59:8080/dialog/patients
// Home IP Address: 192.168.1.136
// http://192.168.1.136:8080/dialog/patients
// http://192.168.1.136:8080/dialog/doctors

// http://localhost:8080/dialog/patients
// http://localhost:8080/dialog/doctors

//Heroku
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/docotrs
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/patients
