<#import "macros/main.ftl" as main>
<@main.cover>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add Exam
    </div>
    <div class="form-style-2">
        <form method="post" action="/teacher/exam/3">
            <label for="type">Type
                <select class="custom-select" name="typeId" id="type">
                    <option selected>Choose type</option>
                <#list examsSubjectsTypes as type>
                    <option value="${type.getId()}">${type.toString()}</option>
                </#list>
                </select>
            </label>
            <br>

            <label for="student">Student
                <select class="custom-select" name="studentId" id="student">
                    <option selected>Choose student</option>
                <#list students as student>
                    <option value="${student.getId()}">${student.getUserInformation().toString()}</option>
                </#list>
                </select>
            </label>
            <br>

            <label for="teacher">Student
                <select class="custom-select" name="teacherId" id="teacher">
                    <option selected>Choose teacher</option>
                <#list teachers as teacher>
                    <option value="${teacher.getId()}">${teacher.getUserInformation().toString()}</option>
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
