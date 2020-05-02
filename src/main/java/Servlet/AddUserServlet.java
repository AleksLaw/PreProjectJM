package Servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addUser")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        if (!name.equals("") && !surname.equals("")) {
            if (userService.addUser(new User(name, surname))) {
                request.getRequestDispatcher("/add.jsp").forward(request, response);
            }
        } else {
            response.getWriter().println("USER NOT ADDED");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
