$(document).ready(function () {

    $('#btSingUp').click(function () {
        var user = {}
        user["userId"] = 0;
        user["lastName"] = $("#lastName").val();
        user["firstName"] = $("#firstName").val();
        user["middleName"] = $("#middleName").val();
        user["birthday"] = $("#birthday").val();

        user["email"] = $("#email").val();
        user["rawPassword"] = $("#rawPassword").val();
        user["repeatRawPassword"] = $("#repeatRawPassword").val();
        user["phone"] = $('#phone').val();
        user["status"] = '';

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "user",
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (responseData) {
                if (responseData != "") {
                    $(".errorSummary").empty().append(responseData);
                } else {
                    location.href = 'message.jsp';
                }
            }
        });
    });
});

