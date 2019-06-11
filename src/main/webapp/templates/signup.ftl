<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
    <@mainHeaders.cover/>
<body>
<@cardContainer.cover>
<div class="form-style-2">
    <div class="form-style-2-heading text-center">
        Please sign up
    </div>
    <#if errors??>
        <#list errors as error>
    <div class="alert-danger">
        ${error.getDefaultMessage()}
    </div>
        </#list>
    </#if>
    <form method="post" action="/signup">
        <label for="email"><span class="col-3">Email</span>
            <input class="input-field" type="text" id="login" name="email">
        </label>
        <br>
        <label for="name"><span class="col-3">Name</span>
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <br>
        <label for="surname"><span class="col-3">Surname</span>
            <input class="input-field" type="text" id="surname" name="surname">
        </label>
        <br>
        <label for="password"><span class="col-3">Password</span>
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <input class="bg-primary" type="submit" value="SignUp">
    </form>
</div>
</@cardContainer.cover>
</body>
</@main.cover>
