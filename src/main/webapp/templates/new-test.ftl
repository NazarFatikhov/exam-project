<#import "macros/main.ftl" as main>
<#import "macros/new-test-headers.ftl" as newTestHeaders>
<#import "macros/card-container.ftl" as cardContainer>
<@main.cover>
    <@newTestHeaders.cover/>
    <body>
        <@cardContainer.cover>
        <div class="card-title mb-4">
            <div class="d-flex justify-content-start">
                <div class="form-style-2 col-12">
                    <div class="col-12 text-center" id="new-exam-form">
                        <form method="post" action="/teacher/exam/new-test">
                            <h6>Student</h6>
                            <input class="form-control" type="text" id="find-student" name="student">
                            <div class="col-12" id="select-student" style="padding: 0">
                                                    <#list studentInformations as userInformation>
                                                        <button type="button"
                                                                class="btn btn-light btn-sm col-12 text-left student-select-button">${userInformation.getName()} ${userInformation.getSurname()}</button>
                                                    </#list>
                            </div>
                            <br>
                            <h6>Type</h6>
                            <select class="custom-select" name="typeId" id="type">
                                                    <#list examsSubjectsTypes as type>
                                                        <#if type.getType().toString() == "TEST">
                                                            <option value="${type.getId()}">${type.toString()}</option>
                                                        </#if>
                                                    </#list>
                            </select>
                            <br><br>
                            <h6>Date</h6>
                            <input class="form-control" type="date" id="date" name="date">
                            <br>
                            <h6>Score</h6>
                            <div class="input-group input-group-sm py-sm-1">
                                <span class="input-group-text p-0">Score</span>
                                <input type='text' class='form-control' aria-label='Text input with checkbox'
                                       name='score'>
                            </div>
                            <input type="submit" value="Save" class="bg-primary">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </@cardContainer.cover>
    </body>
</@main.cover>
