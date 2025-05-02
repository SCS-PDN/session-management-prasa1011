import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get courseId from URL parameter
        String courseId = request.getParameter("courseId");

        // 2. Get current user's session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 3. Add courseId to enrolled courses list in session
        List<String> enrolledCourses = (List<String>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        if (courseId != null && !enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }

        session.setAttribute("enrolledCourses", enrolledCourses);

        // 4. Redirect back to DashboardServlet
        response.sendRedirect("DashboardServlet");
    }
}
