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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = UserService.getInstance();

        List<User> userList = service.getAll();
        request.setAttribute("userList", userList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userList.jsp");
        dispatcher.forward(request, response);
    }
}