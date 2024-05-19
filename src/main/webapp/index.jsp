<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body {
            background-image: url(https://www.wallpaperking.co.uk/12976/star-wars-credits-intro-wall-mural-8-487.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .welcome-text {
            position: absolute;
            top: 50px;
            left: 50%;
            transform: translateX(-50%);
            color: rgba(248, 221, 41, 0.9);
            font-size: 30px;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 300px;
            background-color: rgb(9, 0, 0);
            padding: 20px;
            border-radius: 10px;
            margin-top: -450px;
        }

        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
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
<label class="welcome-text">
    Ласкаво просимо! Тут ви зможете знайти детальну інформацію о персонажах з Зоряних Війн.
</label>
<form action="searchPeople">
    <button type="submit">Почати</button>
</form>
</body>
</html>
