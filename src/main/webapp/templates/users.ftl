<#import "macros/main.ftl" as main>
<@main.cover>
<div class="form-style-2">
    <div class="form-style-2-heading">
        ${userDTO.getUserName()} ${userDTO.getUserSurname()}
    </div>
    <table class="table">
        <tr>
            <th></th>
            <th>Average exam's score</th>
            <th>Last exam's score</th>
            <th>Average test score</th>
            <th>Last test score</th>
        </tr>
        <#list userDTO.getStudentSubjectInformation() as usersSubjectInformation>
        <tr>
            <td><b>${usersSubjectInformation.getExamsSubjectsType().toString()}</b></td>
            <#if usersSubjectInformation.getAverageExamScore()?? ||
                usersSubjectInformation.getLastExamScore()?? ||
                usersSubjectInformation.getAverageTestScore()?? ||
                usersSubjectInformation.getLastTestScore()??>
            <td>${usersSubjectInformation.getAverageExamScore()}</td>
            <td>${usersSubjectInformation.getLastExamScore()}</td>
            </#if>
        </tr>
        </#list>
    </table>
</div>
</@main.cover>