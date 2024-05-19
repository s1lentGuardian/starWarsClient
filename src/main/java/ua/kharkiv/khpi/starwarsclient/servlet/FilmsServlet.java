package ua.kharkiv.khpi.starwarsclient.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kharkiv.khpi.starwarsclient.api.StarWarsAPIClient;
import ua.kharkiv.khpi.starwarsclient.model.Film;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/films")
public class FilmsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmUrlListParam = req.getParameter("listOfFilms");
        String[] filmUrlListArray = filmUrlListParam.split(",");
        List<String> filmUrlList = Arrays.asList(filmUrlListArray);

        List<Film> films = StarWarsAPIClient.getFilms(filmUrlList);

        req.setAttribute("films", films);
        req.getRequestDispatcher("films.jsp").forward(req, resp);
    }
}
