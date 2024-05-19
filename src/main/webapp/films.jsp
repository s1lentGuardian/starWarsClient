<%@ page import="java.util.List" %>
<%@ page import="ua.kharkiv.khpi.starwarsclient.model.Film" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Film> films = (List<Film>) request.getAttribute("films");
%>

<html>
<head>
    <title>Film Page</title>
    <style>
        body {
            background-image: url(https://teamsbackground.net/wp-content/uploads/2020/04/star-wars-backgrounds-25.jpg);
            height: auto;
            margin: 0;
        }

        .film-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            max-height: 80vh;
            overflow-y: auto;
            width: 80%;
            margin: 20px auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        td:first-child {
            background-color: #ccc;
        }
    </style>
</head>
<body>
<%
    for (Film film : films)
    {
%>
<div class="film-container">
    <table style="border: 1px solid">
        <tr>
            <td>Назва</td>
            <td><%= film.getTitle()%></td>
        </tr>

        <tr>
            <td>Номер епізоду</td>
            <td><%= film.getEpisodeID()%></td>
        </tr>

        <tr>
            <td>Передісторія</td>
            <td><%= film.getOpeningCrawl()%></td>
        </tr>

        <tr>
            <td>Режисер</td>
            <td><%= film.getDirector()%></td>
        </tr>

        <tr>
            <td>Продюсер</td>
            <td><%= film.getProducer()%></td>
        </tr>

        <tr>
            <td>Дата релізу</td>
            <td><%= film.getReleaseDate()%></td>
        </tr>
    </table>
</div>
<%
    }
%>

</body>
</html>
