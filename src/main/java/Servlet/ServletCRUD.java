package Servlet;

import model.User;
import service.UserService;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/start")
public class ServletCRUD extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

            response.getWriter().println( "sdasdasdasdasdasdasdasdasd");
     //      response.getWriter().println(   System.getproperty("java.classpath"));
        List<User> list= UserService.getInstance().allUser();

        response.getWriter().println( list);
    }
}