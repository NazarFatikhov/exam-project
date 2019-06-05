<#import "macros/main.ftl" as main>
<@main.cover>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add Exam
    </div>
    <div class="form-style-2-heading" id="test">

    </div>
    <#if errors??>
        <#list errors as error>
            <p class="alert-danger">
                ${error.getDefaultMessage()}
            </p>
        </#list>
    </#if>
    <#if success??>
        <p class="alert-success">
                Exam successfully added
        </p>
    </#if>
    <div class="form-style-2">
        <form method="post" action="/teacher/exam/new">
            <label for="type">Type
                <select class="custom-select" name="typeId" id="type">
                <#list examsSubjectsTypes as type>
                    <option value="${type.getId()}">${type.toString()}</option>
                </#list>
                </select>
            </label>
            <br>

            <label for="student">Student
                <select class="custom-select" name="studentId" id="student">
                <#list studentsInformation as studentInformation>
                    <option value="${studentInformation.getUser().getId()}">${studentInformation.toString()}</option>
                </#list>
                </select>
            </label>
            <br>

            <label for="teacher">Teacher
                <select class="custom-select" name="teacherId" id="teacher">
                <#list teachersInformation as teacherInformation>
                    <option value="${teacherInformation.getUser().getId()}">${teacherInformation.toString()}</option>
                </#list>
                </select>
            </label>
            <br>

            <label for="date">Date
                <input class="date-picker" type="date" id="date" name="date">
            </label>
            <br>
            <#list 0..taskCount-1 as i>
                <label for="task">Task ${i}
                    <input class="input-field" type="text" id="task" value="0" name="scores[${i}]">
                </label>
            </#list>
            <input type="submit" value="Add">
        </form>
    </div>
</div>
</@main.cover>
