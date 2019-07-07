<#macro cover>
<div class="card-title mb-4">
    <div class="d-flex justify-content-start">
        <div class="image-container">
            <img src="/images/no-avatar.png" id="imgProfile" style="width: 150px; height: 150px" сlass="img-thumbnail"/>
            <div class="middle">
                <input type="button" class="btn btn-secondary" id="btnChangePicture" value="Change"/>
                <input type="file" style="display: none;" id="profilePicture" name="file"/>
            </div>
        </div>
        <div class="userData ml-3">
            <h2 class="d-block"
                style="font-size: 1.5rem; font-weight: bold">${userDTO.getUserName()} ${userDTO.getUserSurname()}</h2>
            <h6 class="d-block">Online</h6>

        </div>
        <div class="ml-auto">
            <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes"/>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-12">
        <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                <#list userDTO.getStudentSubjectInformation() as userSubjectInformation>
                                    <li class="nav-item">
                                        <a class="nav-link"
                                           id="Subject_id_${userSubjectInformation.getExamsSubjectsType().getId()}-tab"
                                           data-toggle="tab"
                                           href="#Subject_id_${userSubjectInformation.getExamsSubjectsType().getId()}"
                                           role="tab"
                                           aria-controls="Subject_id_${userSubjectInformation.getExamsSubjectsType().getId()}"
                                           aria-selected="false">${userSubjectInformation.getExamsSubjectsType().toString()}</a>
                                    </li>
                                </#list>

        </ul>
        <div class="tab-content ml-1" id="myTabContent">
                            <#list userDTO.getStudentSubjectInformation() as userSubjectInformation>
                                <div class="tab-pane fade"
                                     id="Subject_id_${userSubjectInformation.getExamsSubjectsType().getId()}"
                                     role="tabpanel"
                                     aria-labelledby="Subject_id_${userSubjectInformation.getExamsSubjectsType().getId()}-tab">
                                    <table class="table table-bordered">
                                        <tbody>
                                                <#if userSubjectInformation.getAverageExamScore()??>
                                                <tr>
                                                    <th>Average exam score</th>
                                                    <td>${userSubjectInformation.getAverageExamScore()}</td>
                                                </tr>
                                                </#if>
                                                <#if userSubjectInformation.getLastExamScore()??>
                                                <tr>
                                                    <th>Last exam score</th>
                                                    <td>${userSubjectInformation.getLastExamScore()}</td>
                                                </tr>
                                                </#if>
                                                <#if userSubjectInformation.getAverageTestScore()??>
                                                <tr>
                                                    <th>Average test score</th>
                                                    <td>${userSubjectInformation.getAverageTestScore()}</td>
                                                </tr>
                                                </#if>
                                                <#if userSubjectInformation.getLastTestScore()??>
                                                <tr>
                                                    <th>Last test score</th>
                                                    <td>${userSubjectInformation.getLastTestScore()}</td>
                                                </tr>
                                                </#if>
                                        </tbody>
                                    </table>
                                </div>
                            </#list>
        </div>
    </div>
</div>
    <#if isTeacher == true>
    <a role="button" class="btn btn-primary" href="/teacher/exam/new-exam">Add new Exam</a>
    <a role="button" class="btn btn-primary" href="/teacher/exam/new-test">Add new Test</a>
    </#if>
    <#if isAdmin == true>
    <a role="button" class="btn btn-primary" href="/admin/subjects">Add new Subject</a>
    <a role="button" class="btn btn-primary" href="/admin/signup-teacher">Add new Teacher</a>
    </#if>
</#macro>
