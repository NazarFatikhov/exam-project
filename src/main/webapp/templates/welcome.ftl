<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
    <@mainHeaders.cover/>
<body xmlns="http://www.w3.org/1999/html">
<@cardContainer.cover>
<#--<span class="text-center">-->
<#--<h1>This is site for subject's skills monitoring</h1>-->
<#--</span>-->
<#--<span class="text-left">-->
<#--<h6>Here you can create account and passing exams or tests. You can monitoring your current average scores and last score for each subject.-->
<#--<br>-->
<#--<br>-->
<#--Also you can get information for each task of your exam or test. Search dynamic and changes.-->
<#--<br>-->
<#--<br>-->
<#--To create account press "Sign Up" or press "Sign In" if you already have account-->
<#--</h6>-->
<#--</span>-->
<#--<a role="button" class="btn btn-primary" href="/signin">Sign In</a>-->
<#--<a role="button" class="btn btn-secondary" href="/signup">Sign Up</a>-->
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <main role="main" class="inner cover">
        <h1 class="cover-heading">This is site for subject's skills monitoring</h1>
        <p class="lead">Here you can create account and passing exams or tests. You can monitoring your current average
            scores and last score for each subject.<br>
            <br>
            Also you can get information for each task of your exam or test. Search dynamic and changes.
            <br>
            <br>
            To create account press "Sign Up" or press "Sign In" if you already have account</p>
        <p class="lead">
            <a href="/signup" class="btn btn-lg btn-secondary">Sign Up</a>
            <a href="/signin" class="btn btn-lg btn-secondary">Sign In</a>
        </p>

    </main>

    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p>Java Spring project.</p>
        </div>
    </footer>
</div>
</@cardContainer.cover>
</body>
</@main.cover>