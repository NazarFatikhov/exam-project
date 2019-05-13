<#import "macros/main.ftl" as main>
<@main.cover>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add Exams types with subjects
    </div>
    <form method="post" action="/signup-teacher">
        <label for="min-score">Type
            <select class="custom-select">
                <option selected>Choose type</option>
                <#list typesFromServer as typeName>
                    <option value="${typeName}">${typeName}</option>
                </#list>
            </select>
        </label>
        <br>
        <label for="min-score">Min score
            <input class="input-field" type="text" id="min-score" name="min-score">
        </label>
        <br>
        <label for="max-score">Max Score
            <input class="input-field" type="text" id="max-score" name="max-score">
        </label>
        <br>
        <label for="tesk-count">Max Score
            <input class="input-field" type="text" id="tesk-count" name="tesk-count">
        </label>
        <br>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <input type="submit" value="Sign Up Teacher">
    </form>
</div>
</@main.cover>
