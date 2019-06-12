<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
    <@mainHeaders.cover/>
<body>
<@cardContainer.cover>
<div class="form-style-2">
    <div class="form-style-2-heading text-center">
        Please Login!
    </div>
    <form method="post" action="/signin">
        <label for="emailAdress"><span class="col-3">Email</span>
            <input class="input-field" type="text" id="login" name="emailAdress">
        </label>
        <br>
        <label for="password"><span class="col-3">Password</span>
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <input class= "bg-primary" type="submit" value="Login">
        <a role="button" class="btn btn-secondary" href="/signup">Sign Up</a>
    </form>
</div>
</div>
</@cardContainer.cover>
</body>
</@main.cover>