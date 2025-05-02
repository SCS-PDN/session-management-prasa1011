import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("student1", "pass1");
        users.put("student2", "pass2");
        users.put("admin", "admin123");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Implement login logic
        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2. Validate credentials (hardcode a few users)
        if (users.containsKey(username) && users.get(username).equals(password)) {
            // 3. If valid:
            //    - Create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //    - Store username in cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // 1 hour
            userCookie.setPath("/");
            response.addCookie(userCookie);

            //    - Redirect to DashboardServlet
            response.sendRedirect("DashboardServlet");
        } else {
            // 4. If invalid, redirect back to login.html
            response.sendRedirect("login.html");
        }
    }