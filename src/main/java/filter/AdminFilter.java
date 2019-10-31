package filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String role = (String) session.getAttribute("role");
        if (role.equals("user")) {
            resp.sendRedirect("/user");
        } else {
            chain.doFilter(request, response);
        }

        /*HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.getAttribute("role") == null) {
            UserService service = UserService.getInstance();

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String role = service.getRole(login, password);

            if (role != null) {
                session.setAttribute("role", role);
                if (role.equals("admin")) {
                    resp.sendRedirect("/admin");
                } else if (role.equals("user")) {
                    resp.sendRedirect("/user");
                }
            } else {
                resp.sendRedirect("/");
            }

        } else {
            String role = (String) session.getAttribute("role");
            if (role.equals("user")) {
                resp.sendRedirect("/user");
            } else {
                chain.doFilter(request, response);
            }
        }*/
    }

    @Override
    public void destroy() {

    }
}