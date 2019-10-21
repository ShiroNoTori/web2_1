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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService();

        /*Stream.iterate(1, x -> x + 1)
                .map(String::valueOf)
                .map(s -> new User(s, s, s))
                .limit(100)
                .forEach(service::add);*/

        List<User> userList = service.getAll();
        request.setAttribute("userList", userList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}