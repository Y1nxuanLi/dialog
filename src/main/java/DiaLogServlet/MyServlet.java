package DiaLogServlet;

import DiaLogApp.Patient;
import com.google.gson.Gson;

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

@WebServlet(urlPatterns={"/","/patients","/doctors","login","register"},loadOnStartup=1)

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.getWriter().write("Welcome to DiaLog");
        String servletPath = req.getServletPath();
        // You can now use the servletPath in your code
        resp.getWriter().println("Servlet Path: " + servletPath);

        switch (servletPath) {
            case "/login":
                resp.getWriter().write("login Page");
                break;
            case "/register":
                resp.getWriter().write("register Page");
                break;
            case "/doctors":
                resp.getWriter().write("Hello, doctor! ");
                break;
            case "/patients":
                resp.getWriter().write("Hello, patient! ");
                break;
            case "/": // default case is for root path ("/")
                resp.getWriter().write("Welcome to the home page! ");
                break;
        }



        String sqlSelect = "SELECT * FROM patients;";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            System.out.println("Patients Data:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String familyName = rs.getString("familyname");
                String givenName = rs.getString("givenname");
                String phoneNumber = rs.getString("phonenumber");
                resp.getWriter().write("ID: " + id + ", Family Name: " + familyName + ", Given Name: " + givenName + ", Phone Number: " + phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{

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
}


// College IP: 146.169.219.59
// http://146.169.219.59:8080/Tutorial_7_server/patients
// Home IP Address: 192.168.1.136
// http://192.168.1.136:8080/Tutorial_7_server/patients
// http://192.168.1.136:8080/Tutorial_7_server/doctors

// http://localhost:8080/Tutorial_7_server/patients
// http://localhost:8080/Tutorial_7_server/doctors

//Heroku
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/docotrs
//https://tutorial5http-fb604ddb8cb8.herokuapp.com/patients
