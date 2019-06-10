<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
<@mainHeaders.cover/>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add Exams types with subjects
    </div>
    <form method="post" action="/admin/exams-subjects-type">
        <label for="type">Type
            <select class="custom-select" name="type" id="type">
                <option selected>Choose type</option>
                <#list examsSubjectsTypeDTO.getTypes() as typeName>
                    <option value="${typeName}">${typeName}</option>
                </#list>
            </select>
        </label>
        <br>
        <label for="minScore">Min score
            <input class="input-field" type="text" id="minScore" name="minScore">
        </label>
        <br>
        <label for="maxScore">Max Score
            <input class="input-field" type="text" id="maxScore" name="maxScore">
        </label>
        <br>
        <label for="taskCount">Task count
            <input class="input-field" type="text" id="taskCount" name="taskCount">
        </label>
        <br>
        <label for="subject">Subject
            <select class="custom-select" name="subject" id="subject">
                <option selected>Choose type</option>
                <#list examsSubjectsTypeDTO.getSubjects() as subject>
                    <option value="${subject.getName()}">${subject.getName()}</option>
                </#list>
            </select>
        </label>
        <br>
        <input type="submit" value="Add exmam's subjet type">
        <br>
    </form>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            Exam's types wiht subject in System!
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Subject</th>
                <th>Type</th>
                <th>Min Score</th>
                <th>Max Score</th>
                <th>Task count</th>
            </tr>
            </thead>
            <tbody>
            <#list examsSubjectsTypeDTO.getExamsSubjectsTypes() as examSubjectType>
            <tr>
                <td>${examSubjectType.getSubject().getName()}</td>
                <td>${examSubjectType.getType()}</td>
                <td>${examSubjectType.getMinScore()}</td>
                <td>${examSubjectType.getMaxScore()}</td>
                <td>${examSubjectType.getTasksCount()}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
</@main.cover>
