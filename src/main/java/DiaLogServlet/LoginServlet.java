package DiaLogServlet;

import DiaLogApp.KeyPairs;
import DiaLogApp.Patient;
import DiaLogApp.UserLoginData;
import DiaLogSQL.UserLoginDataSQL;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;


@WebServlet(urlPatterns={"/home", "/register", "/login", "/UserDataTesting","/Admin"}, loadOnStartup=1)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
//        resp.getWriter().write("Welcome to DiaLog ");
        String servletPath = req.getServletPath();
        // You can now use the servletPath in your code
//        resp.getWriter().println("Servlet Path: " + servletPath);
        UserLoginDataSQL.createTable();
        switch (servletPath) {
            case "/login":
//                resp.getWriter().write("Register Page");
                forwardTo(req, resp, "/login.html");
                break;
            case "/register":
//                resp.getWriter().write("Register Page");
                forwardTo(req, resp, "/register.html");
                break;
            case "/home": // default case is for root path ("/")
                resp.getWriter().write("Welcome to the home page! ");
                break;
            case "/UserDataTesting": // default case is for root path ("/")
                resp.getWriter().write("UserLoginData Display for testing purpose");
                UserLoginDataSQL.displayData(resp);
                break;
            case "/Admin":
                UserLoginDataSQL.insertData("Admin","1234567890");

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

        String servletPath = req.getServletPath();
        // You can now use the servletPath in your code
//        resp.getWriter().println("Servlet Path: " + servletPath);

        switch (servletPath) {
            case "/login":
                KeyPairs<String, String> loginData = getAccountPassword(req,resp);
                String userAccountLogin = loginData.getFirst();
                String userPasswordLogin = loginData.getSecond();
                try {
                    UserLoginDataSQL.checkIdentity(userAccountLogin,userPasswordLogin);
                    sendResponseData(resp);
                    forwardTo(req, resp, "/index.html");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            case "/register":
                KeyPairs<String, String> registerData = getAccountPassword(req,resp);
                String userAccountRegister = registerData.getFirst();
                String userPasswordRegister = registerData.getSecond();
                try {
                    if (UserLoginDataSQL.checkIdentity(userAccountRegister,userPasswordRegister)==0){
                        UserLoginDataSQL.insertData(userAccountRegister,userPasswordRegister);
                        sendResponseData(resp);
                        forwardTo(req, resp, "/login.html");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

//        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Gson gson1 = new Gson();
//        UserLoginData user2 = gson1.fromJson(requestBody, UserLoginData.class);


    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    private KeyPairs<String, String> getAccountPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String jsonData = buffer.toString();

        // Parse the JSON data to DataModel object
        Gson gson = new Gson();
        UserLoginData user1 = gson.fromJson(jsonData, UserLoginData.class);

        // Access key1 and key2
        String userAccount = user1.getUserAccount();
        String userPassword = user1.getUserPassword();
        KeyPairs<String, String> keypairs = new KeyPairs<>(userAccount, userPassword);

        return keypairs;

    }

    private void sendResponseData(HttpServletResponse resp) throws IOException {
        KeyPairs<Integer, String> responseData = new KeyPairs<>(0, "Request processed successfully");

// Setting the response type to JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Sending the JSON response
        PrintWriter out = resp.getWriter();
        out.print(new Gson().toJson(responseData));
        out.flush();
    }

}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

