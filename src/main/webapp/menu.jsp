<%@ page import="ua.kharkiv.khpi.starwarsclient.model.People" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<People> peoples = (List<People>) request.getAttribute("peoples");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Menu Page</title>
    <style>
        body {
            background-image: url(https://www.sgclark.com/blog/wp-content/uploads/2023/03/starwars_a_long_time_ago_stars.png);
            background-size: cover;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 300px;
            background-color: rgb(9, 0, 0);
            padding: 20px;
            border-radius: 10px;
            margin-top: -300px;
        }

        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        label {
            margin-bottom: 10px;
            color: rgba(248, 221, 41, 0.9);
            font-size: 20px;
        }

        select, button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            margin-bottom: 10px;
        }

        button {
            background-color: rgba(248, 221, 41, 0.9);
            color: #090000;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<form action="search">
    <label style="font-size: 25px;">
        Оберіть персонажа
        <select name="keyword">
            <% for(People person : peoples) { %>
            <option value="<%= person.getId() %>"><%= person.getName() %></option>
            <% } %>
        </select>
    </label>

    <button type="submit">Знайти</button>
</form>
</body>
</html>
