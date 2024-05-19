package ua.kharkiv.khpi.starwarsclient.api;

import jakarta.json.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import ua.kharkiv.khpi.starwarsclient.Utils;
import ua.kharkiv.khpi.starwarsclient.model.Film;
import ua.kharkiv.khpi.starwarsclient.model.People;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StarWarsAPIClient {

    private static final String SEARCH_API_URL = "https://swapi.dev/api/people";
    private static final Integer PAGE_COUNT = 9;

    public static People searchPeopleByKeyword(String keyword) throws PeopleNotFoundException {
        //Створюємо об'єкти, необхідні для надсилання запиту на API за допомогою специфікації Jakarta RESTful Web Services.
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SEARCH_API_URL);
        target = target.path(keyword);

        Invocation invocation = target.request().buildGet();

        String body;

        try (Response response = invocation.invoke()) { //виклик API
            body = response.readEntity(String.class); //отримання відповіді API у вигляді рядку
        }

        //Після отримання відповіді від API оброблюємо її за допомогою специфікації Jakarta JSON Processing.
        JsonReader jsonReader = Json.createReader(new StringReader(body));

        /* Результат повертається у вигляді JSON-об'єкту, поле name якого містить знайдені нас.пункти.
        Тому спочатку отримуємо цей об'єкт і після цього зчитуємо з нього потрібний нам елемент */
        JsonObject jsonObject = jsonReader.readObject();

        if (jsonObject.containsKey("name")) {
            return parseToPeople(jsonObject); // Преобразуем JSON в объект People
        } else {
            throw new PeopleNotFoundException();
        }
    }


    private static WebTarget setQueryParams(WebTarget target, Integer keyword) {
        return target.queryParam("page", keyword);
    }


    private static People parseToPeople(JsonObject jsonObject) {
        People people = new People();

        people.setName(jsonObject.getString("name"));
        people.setHeight(jsonObject.getString("height"));
        people.setMass(jsonObject.getString("mass"));
        people.setHairColor(jsonObject.getString("hair_color"));
        people.setSkinColor(jsonObject.getString("skin_color"));
        people.setEyeColor(jsonObject.getString("eye_color"));
        people.setBirthYear(jsonObject.getString("birth_year"));
        people.setGender(jsonObject.getString("gender"));

        JsonArray filmsArray = jsonObject.getJsonArray("films");
        List<String> films = new ArrayList<>();
        for (JsonValue film : filmsArray) {
            films.add(film.toString()); // Добавляем URL фильма в список
        }
        people.setFilms(films);
        String url = jsonObject.getString("url");
        people.setId(Utils.extractIdFromUrl(url));
        System.out.println("-------------------id------------");
        System.out.println(people.getId());
        System.out.println("-------------------id------------");
        return people;
    }

    public static List<Film> getFilms(List<String> filmList) {
        List<Film> films = new ArrayList<>();

        for (String filmUrl : filmList) {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(filmUrl);
            target = setQueryParams(target,1);

            Invocation invocation = target.request().buildGet();

            String body;

            try (Response response = invocation.invoke()) {
                body = response.readEntity(String.class);
            }

            JsonReader jsonReader = Json.createReader(new StringReader(body));
            JsonObject jsonObject = jsonReader.readObject();

            Film film = parseToFilm(jsonObject);

            films.add(film);
        }

        return films;
    }

    private static Film parseToFilm(JsonObject jsonObject) {
        Film film = new Film();
        film.setTitle(jsonObject.getString("title"));
        film.setEpisodeID(jsonObject.getInt("episode_id"));
        film.setOpeningCrawl(jsonObject.getString("opening_crawl"));
        film.setDirector(jsonObject.getString("director"));
        film.setProducer(jsonObject.getString("producer"));
        film.setReleaseDate(LocalDate.parse(jsonObject.getString("release_date")));
        return film;
    }

    public static List<People> searchAllPeople() throws PeopleNotFoundException {
        Client client = ClientBuilder.newClient();
        List<People> allPeople = new ArrayList<>();

        for (int page = 1; page <= PAGE_COUNT; page++) {
            WebTarget target = client.target(SEARCH_API_URL);
            target = target.queryParam("page", page);

            Invocation invocation = target.request().buildGet();

            String body;

            try (Response response = invocation.invoke()) {
                body = response.readEntity(String.class);
            }

            JsonReader jsonReader = Json.createReader(new StringReader(body));
            JsonObject jsonObject = jsonReader.readObject();

            if (jsonObject.containsKey("results")) {
                JsonArray jsonArray = jsonObject.getJsonArray("results");
                List<People> pagePeople = jsonArray.stream().map(item -> parseToPeople(item.asJsonObject())).collect(Collectors.toList());
                allPeople.addAll(pagePeople);
            } else {
                throw new PeopleNotFoundException();
            }
        }

        return allPeople;
    }

}
