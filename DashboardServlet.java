import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Implement dashboard logic
        // 1. Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }
        // 2. Create a list of courses (hardcoded)
        List<Course> courses = Arrays.asList(
                new Course("CSC1013", "Intro to Computer Science", "Dr.kamal"),
                new Course("CSC2021", "Data Structures", "Prof.Leeshan"),
                new Course("CSC3103", "Web Programming", "Dr.saman")
        );
        // 3. Store courses in request attribute
        request.setAttribute("courses", courses);
        // 4. Forward to dashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}