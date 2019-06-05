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
                                <div class="col-12 text-center">
                                    <h6>Student</h6>
                                    <form method="post" action="/teacher/exam/new">
                                        <input class="form-control" type="text" id="find-student">
                                        <div class="col-12" id="select-student">
                                            <#list usersInformations as userInformation>
                                                <button type="button" class="btn btn-light btn-sm col-12 text-left student-select-button">${userInformation.getName()} ${userInformation.getSurname()}</button>
                                            </#list>
                                        </div>
                                        <input class="form-control d-none" type="text" id="find-teacher">
                                        <div class="col-12" id="select-teacher">
                                            <#list usersInformations as userInformation>
                                                <button type="button" class="btn btn-light btn-sm col-12 text-left student-select-button">${userInformation.getName()} ${userInformation.getSurname()}</button>
                                            </#list>
                                        </div>
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
</@main.cover>