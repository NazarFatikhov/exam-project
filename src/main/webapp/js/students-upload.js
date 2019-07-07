$(document).ready(function () {
    $("input[id='find-student']").on("input", function (event) {
        $("div[id='select-student']").removeClass('d-none')
        $("div[id='select-student']").empty()
        var startStr = this.value
        sendStudentRequest(startStr);
    })
    updateStudentList()
})

function sendStudentRequest(startStr) {
    $.ajax({
        type: "POST",
        url: "/teacher/exam/get-students",
        data: "startString=" + startStr,
        success: function (result) {
            var studentsCount = result.length
            if (studentsCount > 4) {
                studentsCount = 4
            }
            for (i = 0; i < studentsCount; i++) {
                $("div[id='select-student']").append($('<button>',
                    {
                        class: "btn btn-light btn-sm col-12 text-left student-select-button",
                        type: "button",
                        text: result[i].name + " " + result[i].surname
                    }));
            }
            updateStudentList()
        },
        error: function (response) {
            $("div[id='select-student']").append($('<button>',
                {
                    class: "btn btn-light btn-sm col-12 text-left student-select-button",
                    type: "button",
                    text: "Problems with server"
                }));
        }
    });
}

function updateStudentList() {
    $("button.student-select-button").each(function () {
        $(this).click((function (event) {
            $("input[id='find-student']").val(this.innerText)
            $("div[id='select-student']").addClass('d-none')
        }))
    })
}
