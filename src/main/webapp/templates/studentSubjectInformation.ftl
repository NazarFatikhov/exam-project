<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
    <@mainHeaders.cover/>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add student's subject's information
    </div>
    <form method="post" action="/admin/student-subject-information">
        <label for="student">Student
            <select class="custom-select" name="student" id="student">
                <option selected>choose student</option>
                <#list students as student>
                    <option value="${student.getId()}">${student.getId()}</option>
                </#list>
            </select>
        </label>
        <br>
        <label for="examsSubjectsType">Type
            <select class="custom-select" name="examsSubjectsType" id="examsSubjectsType">
                <option selected>choose type and subject</option>
                <#list examsSubjectsTypes as subjectsWithType>
                    <option value="${subjectsWithType.getId()}">${subjectsWithType.getSubject().getName()}</option>
                </#list>
            </select>
        </label>
        <br>
        <label for="averageExamScore">Average Exam Score
            <input class="input-field" type="text" id="averageExamScore" name="averageExamScore">
        </label>
        <br>
        <label for="averageTestScore">Average Test Score
            <input class="input-field" type="text" id="averageTestScore" name="averageTestScore">
        </label>
        <br>
        <label for="lastExamScore">Last Exam Score
            <input class="input-field" type="text" id="lastExamScore" name="lastExamScore">
        </label>
        <br>
        <label for="lastTestScore">Last Test Score
            <input class="input-field" type="text" id="lastTestScore" name="lastTestScore">
        </label>
        <br>
        <input type="submit" value="Add">
        <br>
    </form>
</div>
</body>
</@main.cover>