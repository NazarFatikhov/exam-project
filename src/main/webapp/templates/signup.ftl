<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please sign up
    </div>
    <form method="post" action="/signup">
        <label for="email">Email
            <input class="input-field" type="text" id="login" name="email">
        </label>
        <br>
        <label for="name">Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <br>
        <label for="surname">Surname
            <input class="input-field" type="text" id="surname" name="surname">
        </label>
        <br>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <input type="submit" value="SignUp">
    </form>
</div>
</body>
</html>
