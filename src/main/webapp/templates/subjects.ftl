<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
    <@mainHeaders.cover/>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add subject
    </div>
    <form method="post" action="/admin/add-subject">
        <label for="name">
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <input type="submit" value="Add subject">
    </form>
    <div class="form-style-2-heading">
        Subjects
    </div>
    <table>
        <tr>
            <th>Name</th>
            <th>Status</th>
        </tr>
        <#list subjectsFromServer as subject>
        <tr>
            <td>${subject.getName()}</td>
            <td>${subject.getState()}</td>
        </tr>
        </#list>
    </table>

</div>
</body>
</@main.cover>
