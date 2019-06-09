<#import "macros/main.ftl" as main>
<@main.cover>
<div class="container">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <div class="card-title mb-4">
                        <div class="d-flex justify-content-start">
                            <div class="form-style-2 col-12">
                                <div class="col-12 text-center" id="new-exam-form">
                                    <form method="post" action="/teacher/exam/new-exam">
                                        <h6>Student</h6>
                                        <input class="form-control" type="text" id="find-student" name="student">
                                        <div class="col-12" id="select-student" style="padding: 0">
                                            <#list studentInformations as userInformation>
                                                <button type="button" class="btn btn-light btn-sm col-12 text-left student-select-button">${userInformation.getName()} ${userInformation.getSurname()}</button>
                                            </#list>
                                        </div>
                                        <br>
                                        <h6>Teacher</h6>
                                        <input class="form-control" type="text" id="find-teacher" name="teacher">
                                        <div class="col-12" id="select-teacher" style="padding: 0">
                                            <#list teacherInformations as userInformation>
                                                <button type="button" class="btn btn-light btn-sm col-12 text-left teacher-select-button">${userInformation.getName()} ${userInformation.getSurname()}</button>
                                            </#list>
                                        </div>
                                        <br>
                                        <h6>Type</h6>
                                        <select class="custom-select" name="typeId" id="type">
                                            <#list examsSubjectsTypes as type>
                                                <option value="${type.getId()}">${type.toString()}</option>
                                            </#list>
                                        </select>
                                        <br><br>
                                        <h6>Date</h6>
                                        <input class="form-control" type="date" id="date" name="date">
                                        <br>
                                        <h6>Tasks</h6>
                                        <div class="col-12 text-left p-0 d-none" id="subject-tasks-part-1">
                                        </div>
                                        <div class="col-12 text-left p-0 d-none" id="subject-tasks-part-2">
                                        </div>
                                        <input type="submit" value="Save" class="bg-primary">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/students-upload.js"></script>
<script type="text/javascript" src="/js/teacher-upload.js"></script>
<script type="text/javascript" src="/js/subject-tasks-upload.js"></script>
<script type="text/javascript" src="/js/input-remover.js"></script>
</@main.cover>