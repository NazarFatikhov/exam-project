$(document).ready(function () {
    $("input[id='find-teacher']").on("input", function (event) {
        $("div[id='select-teacher']").removeClass('d-none')
        $("div[id='select-teacher']").empty()
        var startStr = this.value
        sendTeacherRequest(startStr);
    })
    updateTeacherList()
})

function sendTeacherRequest(startStr) {
    $.ajax({
        type: "POST",
        url: "/teacher/exam/get-teachers",
        data: "startString=" + startStr,
        success: function (result) {
            var teachersCount = result.length
            if (teachersCount > 4) {
                teachersCount = 4
            }
            for (i = 0; i < teachersCount; i++) {
                $("div[id='select-teacher']").append($('<button>',
                    {
                        class: "btn btn-light btn-sm col-12 text-left teacher-select-button",
                        type: "button",
                        text: result[i].name + " " + result[i].surname
                    }));
            }
            updateTeacherList()
        },
        error: function (response) {
            $("div[id='select-teacher']").append($('<button>',
                {
                    class: "btn btn-light btn-sm col-12 text-left teacher-select-button",
                    type: "button",
                    text: "Problems with server"
                }));
        }
    });
}

function updateTeacherList() {
    $("button.teacher-select-button").each(function () {
        $(this).click((function (event) {
            $("input[id='find-teacher']").val(this.innerText)
            $("div[id='select-teacher']").addClass('d-none')
        }))
    })
}