$(document).ready(function () {

    $('#form-login').submit(function (e) {
        console.log("Submit");

        var username = $("#email").val();
        var password = $("#password").val();
        var data = '{"username":"' + username + '", "password":"' + password + '"}';

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "login",
            data: JSON.stringify(data),
            dataType: 'json',
            headers: {
                "Authorization": "Basic " + btoa(username + ":" + password)
            },
            success: function (responseData) {
                window.location.replace("/driverMap");
            }
        });

        e.preventDefault();
    });


    $('#btSingUp').click(function () {
        var user = {};
        user["userId"] = 0;
        user["email"] = $("#email").val();
        user["rawPassword"] = $("#rawPassword").val();
        user["repeatRawPassword"] = $("#repeatRawPassword").val();
        user["firstName"] = $("#firstName").val();
        user["lastName"] = $("#lastName").val();
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

    $('#btnRestorePassword').click(function () {
        var userEmail = {};

        userEmail["email"] = $("#email").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "restorePassword",
            data: JSON.stringify(userEmail),
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

