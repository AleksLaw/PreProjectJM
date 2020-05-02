package Servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delUser")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        User user = new User(name, surname);

        if (!name.equals("") && !surname.equals("")&& userService.findUser(user)!=null) {
            if (userService.delUser(user)) {
                request.getRequestDispatcher("/delete.jsp").forward(request, response);
            }
        } else {
            response.getWriter().println("USER NOT DELETED");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
