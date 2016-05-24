import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Laurens Visser
 */
@WebServlet(name = "AantalActiekaartenServlet", urlPatterns = {"/AantalActiekaartenServlet"})
public class AantalActiekaartenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        PrintWriter out = response.getWriter();



        ArrayList<Integer> list = new ArrayList<>();


        for(Kaart k : spel.getAlleKaarten()){
            list.add(spel.getStapelskaarten().get(k.getNr()));
        }

        String json = gson.toJson(list);
        out.print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}