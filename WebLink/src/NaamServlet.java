
import com.company.TestSpel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Yentl-PC on 17/05/2016.
 */
@WebServlet(name = "NaamServlet", urlPatterns = {"/NaamServlet"})
public class NaamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String naamspeler1 = request.getParameter("speler1");
        String naamspeler2 = request.getParameter("speler2");
        response.sendRedirect("/gamepagina.jsp");
        TestSpel testSpel = new TestSpel();
        /*Spel spel = new Spel();
        Speler speler1 = new Speler(naamspeler1);
        Speler speler2 = new Speler(naamspeler2);
        out.println("<h1>"+ speler1 + "</h1>");*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}