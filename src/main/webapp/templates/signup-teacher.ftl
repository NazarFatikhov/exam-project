<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
<@mainHeaders.cover/>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Create teacher
    </div>
    <form method="post" action="/signup-teacher">
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
        <input type="submit" value="Sign Up Teacher">
    </form>
</div>
</body>
</@main.cover>
