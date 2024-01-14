package DiaLogApp.Admin;


import DiaLogServlet.UserServlet.UserDataSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/UserDataTesting"}, loadOnStartup=1)
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String servletPath = req.getServletPath();
        UserDataSQL.createTable();
        switch (servletPath) {

            case "/UserDataTesting":
//                resp.getWriter().write("UserLoginData Display for testing purpose: \n");

                UserDataSQL.displayUserData(resp);

//                UserData testUserData = new UserData();
//
//                testUserData.setUserAccount("admin");
//                testUserData.setUserPassword("12345678");
//                testUserData.setUserConfirmedPassword("12345678");
//                testUserData.setId("1");
//                testUserData.setUserName("");
//                testUserData.setAddress("");
//                testUserData.setEmail("");
//                testUserData.setGender("");
//                testUserData.setDiabetesType("");
//                testUserData.setInsulinType("");
//                testUserData.setPhoneNumber("");
//                testUserData.setDoctorNumber("");
//                testUserData.setPostalCode("");
//
//                UserDataSQL.updateUser(testUserData);

                break;

            default:
                resp.getWriter().write("404 Not Found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}


// http://localhost:8080/dialog/login
// http://localhost:8080/dialog/register

//Heroku
//https://dialog-1d1125195912.herokuapp.com/home
//https://dialog-1d1125195912.herokuapp.com/login
//https://dialog-1d1125195912.herokuapp.com/register

