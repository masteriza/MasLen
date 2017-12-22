$(document).ready(function () {

    $("#form-signin").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var data = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };
        // var username = $form.find('input[name="username"]').val();
        // var password = $form.find('input[name="password"]').val();

        $.ajax({
            type: "POST",
            // contentType: "application/json",
            url: "/login",
            // headers: {
            //     'Authorization': 'Basic ' + btoa(username + ':' + password)
            // },
            // data: JSON.stringify(data),
            data: data,
            // dataType: 'json',
            success: function (responseData) {
                window.location.replace("/driverMap");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    console.log("Fail");
                    alert(jqXHR.status);
                }
            }
        });
    });

    // $('#form-login').submit(function (e) {
    //     console.log("Submit");
    //     // CSRF Token
    //     // var _csrf = $('meta[name="_csrf"]').attr('content');
    //
    //     var username = $("#email").val();
    //     var password = $("#password").val();
    //     var data = '{"username":"' + username + '", "password":"' + password + '"}';
    //
    //     // Validator Username, password.
    //     // Ex: if (username < 3 || password < 6 || ....)
    //     // return;
    //
    //     $.ajax({
    //         type: "POST",
    //         contentType: "application/json",
    //         url: "login",
    //         // headers: {
    //         //     'X-CSRF-TOKEN': _csrf
    //         // },
    //         // data: JSON.stringify({"username": username, "password": password}),
    //         data: JSON.stringify(data),
    //         dataType: 'json',
    //         success: function (responseData) {
    //             window.location.replace("/driverMap");
    //         }
    //         // ,
    //         // error: function (jqXHR, textStatus, errorThrown) {
    //         //     console.log("Fail");
    //         // }
    //     });
    //
    //     e.preventDefault();
    // });


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

