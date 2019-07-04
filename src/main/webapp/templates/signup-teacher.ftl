<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
<@mainHeaders.cover/>
<body>
<body>
        <#if errors??>
            <#list errors as error>
    <div class="alert-danger">
        ${error.getDefaultMessage()}
    </div>
            </#list>
        </#if>
<div class="container">
    <div class="py-5 text-left">

        <h2>Sign up</h2>

    </div>

    <div class="row">

        <div class="col-md-8 order-md-1">
            <form class="needs-validation" novalidate method="post" action="/admin/signup-teacher">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" id="firstName" placeholder="" value="" name="name" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" id="lastName" placeholder="" value="" name="surname"required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">Email <span class="text-muted">(Optional)</span></label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com" name="email">
                </div>
                <div class="mb-3">
                    <label for="password">Password</label>
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">SIGN UP</button>
            </form>
        </div>
    </div>
</body>
</@main.cover>
