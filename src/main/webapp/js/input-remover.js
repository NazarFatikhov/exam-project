$(document).ready(function () {
    $("div[id=subject-tasks-part-1]").on("change", function () {
        var inputs = $("input[type='checkbox']")
        for (i = 0; i < inputs.length; i++) {
            if($("input[type='checkbox'][name='scores[" + i + "]'").is(":checked")){
                $("input[type='hidden'][name='scores[" + i + "]'").attr('disabled', true)
            }else{
                $("input[type='hidden'][name='scores[" + i + "]'").attr('disabled', false)
            }

        }
    })
})