<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
    <@mainHeaders.cover>
        <link href="/css/signin.css" rel="stylesheet">
    </@mainHeaders.cover>
<body>
<form method="post" class="form-signin" action="/signin">
    <h1 class="h3 mb-3" id="main">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" name="emailAdress">
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password">
    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in"/>
    <div class="inner">
        <p class="mt-5 mb-3 " style="color: black">&copy; 2019</p></div>
</form>
</body>
</@main.cover>