<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Login!
    </div>
    <form method="post" action="/signin">
        <label for="emailAdress">
            <input class="input-field" type="text" id="login" name="emailAdress">
        </label>
        <br>
        <label for="password">
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">
        </label>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
