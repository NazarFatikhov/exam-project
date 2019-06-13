<#import "macros/main.ftl" as main>
<#import "macros/profile-headers.ftl" as profileHeaders>
<#import "macros/profile-card-container.ftl" as profileCardContainer>
<#import "macros/card-container.ftl" as cardContainer>
<#import "macros/profile-inside-body.ftl" as profileInsideBody>
<@main.cover>
    <@profileHeaders.cover/>
<body>
    <#if isTeacher == true>
    <@cardContainer.cover>
        <@profileInsideBody.cover/>
    </@cardContainer.cover>
    <#else>
    <@profileCardContainer.cover>
        <@profileInsideBody.cover/>
    </@profileCardContainer.cover>
    </#if>
</body>
</@main.cover>