<#import "macros/main.ftl" as main>
<#import "macros/main-headers.ftl" as mainHeaders>
<@main.cover>
<@mainHeaders.cover/>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add Exam type task
    </div>
    <div class="form-style-2">
        <form method="post" action="/admin/exam-type-task">
            <label for="type">Type
                <select class="custom-select" name="typeId" id="type">
                    <option selected>Choose type</option>
                <#list examsTypes as type>
                    <option value="${type.getId()}">${type.toString()}</option>
                </#list>
                </select>
            </label>
            <br>


            <label for="taskNumber">taskNumber
                <input class="input-field" type="taskNumber" id="taskNumber" name="taskNumber">
            </label>
            <br>

            <label for="minScore">minScore
                <input class="input-field" type="minScore" id="minScore" name="minScore">
            </label>
            <br>

            <label for="maxScore">maxScore
                <input class="input-field" type="maxScore" id="maxScore" name="maxScore">
            </label>
            <br>

            <input type="submit" value="Add">
        </form>
    </div>
</div>
</body>
</@main.cover>
