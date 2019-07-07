$(document).ready(function () {
    $("select[id='type']").on("change", function () {
        $("div[id='subject-tasks-part-1']").empty()
        $("div[id='subject-tasks-part-2']").empty()
        var subjectId = $("select[id='type'] option:selected").val()
        $.ajax({
            type: "POST",
            url: "/teacher/subject-tasks",
            data: "subjectId=" + subjectId,
            success: function (result) {
                if (result[0].examsSubjectsType.type == "FINAL_EXAM" || result[0].examsSubjectsType.type == "CONTROL_WORK") {
                    $("div[id='subject-tasks-part-1']").append($('<h6>1 Part</h6>'))
                    $("div[id='subject-tasks-part-2']").append($('<h6>2 Part</h6>'))
                    $("div[id='subject-tasks-part-1']").removeClass("d-none")
                    $("div[id='subject-tasks-part-2']").removeClass("d-none")
                    for (i = 0; i < result.length; i++) {
                        if (result[i].maxScore == '1') {
                            $("div[id='subject-tasks-part-1']").append($('<label>',
                                {
                                    class: "checkbox inline indent d-inline-block",
                                    html: "<input type='hidden' value='0' name='scores[" + (i).toString() + "]'/>" + "<input type='checkbox' value='1' name='scores[" + (i).toString() + "]'>" + (i + 1).toString()
                                }))
                        } else {
                            $("div[id='subject-tasks-part-2']").append($('<div>',
                                {
                                    class: "input-group input-group-sm py-sm-1",
                                    html: "<span class=\"input-group-text p-0\">" + (i + 1).toString() + "</span>" + "<input type='text' class='form-control' aria-label='Text input with checkbox' name='scores[" + (i).toString() + "]'>"
                                }))
                        }
                    }
                }
            },
            error: function () {
                alert(error)
            }
        })
    })
})