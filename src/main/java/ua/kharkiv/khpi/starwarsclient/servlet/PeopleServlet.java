package ua.kharkiv.khpi.starwarsclient.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kharkiv.khpi.starwarsclient.api.PeopleNotFoundException;
import ua.kharkiv.khpi.starwarsclient.api.StarWarsAPIClient;
import ua.kharkiv.khpi.starwarsclient.model.People;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchPeople")
public class PeopleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<People> peoples = null;
        try {
            peoples = StarWarsAPIClient.searchAllPeople();
            req.setAttribute("peoples", peoples);
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } catch (PeopleNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Жодного персонажа не знайедено");
        }

    }
}
