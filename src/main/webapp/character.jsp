<%@ page import="java.util.List" %>
<%@ page import="ua.kharkiv.khpi.starwarsclient.model.People" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    People people = (People) request.getAttribute("people");
%>

<html>
<head>
    <title>Character Page</title>
    <style>
        body {
            background-image: url(https://lumiere-a.akamaihd.net/v1/images/star-wars-backgrounds-14_856985d9.jpeg);
            background-size: cover;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
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

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .button-container button {
            background-color: yellow;
            color: black;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .button-container button:hover {
            background-color: #f0e68c;
        }
    </style>
</head>
<body>

<div class="container">
    <table>
        <tr>
            <th>Назва</th>
            <td><%= people.getName()%></td>
        </tr>

        <tr>
            <th>Зріст</th>
            <td><%= people.getHeight()%></td>
        </tr>

        <tr>
            <th>Вага</th>
            <td><%= people.getMass()%></td>
        </tr>

        <tr>
            <th>Колір волосся</th>
            <td><%= people.getHairColor()%></td>
        </tr>

        <tr>
            <th>Колір шкіри</th>
            <td><%= people.getSkinColor()%></td>
        </tr>

        <tr>
            <th>Колір очей</th>
            <td><%= people.getEyeColor()%></td>
        </tr>

        <tr>
            <th>Рік народження</th>
            <td><%= people.getBirthYear()%></td>
        </tr>

        <tr>
            <th>Стать</th>
            <td><%= people.getGender()%></td>
        </tr>
    </table>

    <div class="button-container">
        <form id="filmsForm" action="films">
            <input id="listOfFilms" name="listOfFilms" type="hidden">
            <button type="submit">Появлення у фільмах</button>
        </form>
    </div>
</div>

<script>
    var films = <%= people.getFilms() %>;

    // Создание строки со списком ссылок
    var filmUrlList = "";
    for (var i = 0; i < films.length; i++) {
        filmUrlList += films[i];
        if (i < films.length - 1) {
            filmUrlList += ",";
        }
    }

    document.getElementById("listOfFilms").value = filmUrlList;
</script>
</body>
</html>
