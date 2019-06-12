<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
<@mainHeaders.cover/>
<body xmlns="http://www.w3.org/1999/html">
<@cardContainer.cover>
    <span class="text-center">
        <h1>This is site for subject's skills monitoring</h1>
    </span>
    <span class="text-left">
        <h6>Here you can create account and passing exams or tests. You can monitoring your current average scores and last score for each subject.
            <br>
            <br>
            Also you can get information for each task of your exam or test. Search dynamic and changes.
            <br>
            <br>
            To create account press "Sign Up" or press "Sign In" if you already have account
        </h6>
    </span>
    <a role="button" class="btn btn-primary" href="/signin">Sign In</a>
    <a role="button" class="btn btn-secondary" href="/signup">Sign Up</a>
</@cardContainer.cover>
</body>
</@main.cover>