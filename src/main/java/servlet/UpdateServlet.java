package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/userUpdate")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService service = UserService.getInstance();

        if (service.hasId(id)) {
            User user = service.getById(id);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userUpdate.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserService service = UserService.getInstance();

        User user = service.getById(id);
        user.setName(name);
        user.setPassword(password);

        service.update(user);

        response.sendRedirect("/admin");
    }
}