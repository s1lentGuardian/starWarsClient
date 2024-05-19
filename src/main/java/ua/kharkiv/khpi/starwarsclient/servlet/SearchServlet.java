package ua.kharkiv.khpi.starwarsclient.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kharkiv.khpi.starwarsclient.api.PeopleNotFoundException;
import ua.kharkiv.khpi.starwarsclient.api.StarWarsAPIClient;
import ua.kharkiv.khpi.starwarsclient.model.Film;
import ua.kharkiv.khpi.starwarsclient.model.People;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String keyword = req.getParameter("keyword");
        System.out.println("-------------keyword--------");
        System.out.println(keyword);
        System.out.println("-------------keyword--------");
        People people = null;
        try {
            people = StarWarsAPIClient.searchPeopleByKeyword(keyword);
            req.setAttribute("people", people);
            req.getRequestDispatcher("character.jsp").forward(req, resp);
        } catch (PeopleNotFoundException e) {
            //Віддаємо HTTP 404 помилку з нашим власним текстом. Вебконтейнер відобразить власну сторінку помилки.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Жодного персонажа не знайедено за ключовим словом");
        }

    }


}
