$(document).ready(function () {

    $("#form-signin").submit(function (event) {
        var $form = $(this);
        var data = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        $.ajax({
            type: "POST",
            url: "/login",
            data: data,
            success: function (responseData) {
                //window.location.replace("/userPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    console.log("Fail");
                    alert(jqXHR.status);
                }
            }
        });
    });

    $('#logout').click(function () {
        // $.ajax({
        //     type: "GET",
        //     url: "/logout",
        //     success: function (responseData) {
        //     },
        //     error: function (jqXHR, textStatus, errorThrown) {
        //         if (jqXHR.status === 401) {
        //             console.log("Fail logout");
        //             alert(jqXHR.status);
        //         }
        //     }
        // });
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
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    alert(jqXHR.status);
                }
            }
        });
    });
});

