var userObj = {
    "id": $('#characterId')
}

var url = "localhost:8081/" + $('#characterId') + "/healhp";

$(document).ready(function () {
    $('#healhp').keydown(function () {
        var saveData = $.ajax({
            type: 'POST',
            url: "healhp",
            headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
            data: null,
            dataType: "text",
            success: function () {
                console.log("Save Complete")
            }
        });
    })
})