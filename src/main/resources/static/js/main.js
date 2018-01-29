$(document).ready(function () {

    // $("#form-restore").click(function (event) {
    //     var $form = $(this);
    //     var data = {
    //         username: $form.find('input[name="email"]').val(),
    //     };
    //
    //     $.ajax({
    //         type: "POST",
    //         contentType: "application/json",
    //         url: "restorePassword",
    //         data: JSON.stringify(data),
    //         dataType: 'json',
    //         success: function (responseData) {
    //             //window.location.replace("/userPanel");
    //         },
    //         error: function (jqXHR, textStatus, errorThrown) {
    //             if (jqXHR.status === 401) {
    //                 console.log("Fail");
    //                 alert(jqXHR.status);
    //             }
    //         }
    //     });
    // });


    $('#logout').click(function () {
        document.location.href = "/logout";
    });

    $('#btDriverMap').click(function () {
        document.location.href = "/driverMap";
    });

    $('#btPassengerMap').click(function () {
        document.location.href = "/passengerMap";
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

        $('.errorspan').each(function () {
            $(this).text("");
        });

        userEmail["email"] = $("#email").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "restorePassword",
            data: JSON.stringify(userEmail),
            dataType: 'json',
            success: function (responseData) {
                if (responseData !== "" || responseData != null) {
                    if (responseData.status === "FAIL") {
                        console.log("FAIL : ", responseData);
                        for (i = 0; i < responseData.errorList.length; i++) {
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text();
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text(responseData.errorList[i].defaultMessage);
                        }
                    } else if (responseData.status === "OK") {
                        console.log("OK : ", responseData);
                        document.location.href = "/restorePasswordStart";
                    }


                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    alert(jqXHR.status);
                }
            }
        });
    });

    $('#btnRegistration').click(function () {

        $('.errorspan').each(function () {
            $(this).text("");
        });

        var birthday_Day = $("#birthday_Day option:selected").val();
        var birthday_Month = $("#birthday_Month option:selected").val();
        var birthday_Year = $("#birthday_Year option:selected").val();

        var user = {};
        user["lastName"] = $("#lastName").val();
        user["firstName"] = $("#firstName").val();
        user["middleName"] = $("#middleName").val();

        try {
            user["birthday"] = new Date(Date.UTC(birthday_Year, birthday_Month, birthday_Day)).toISOString().substring(0, 10);
        } catch (e) {
            alert("Дата не валидна и там надо починить нормальное отображение на экране")
            $("#birthday").siblings(".errorspan").text("Не заполнена или неверная дата");
            return;
        }
        user["email"] = $("#email").val();
        user["rawPassword"] = $("#rawPassword").val();
        user["repeatRawPassword"] = $("#repeatRawPassword").val();
        user["phone"] = $('#phone').val();
        if ($('input[name=sexRadios]:checked').val() == undefined) {
            user["sex"] = "X";
        } else {
            user["sex"] = $('input[name=sexRadios]:checked').val();
        }
        user["agree"] = document.getElementById("agree").checked;

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "user",
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (responseData) {
                if (responseData !== "" || responseData != null) {
                    if (responseData.status === "FAIL") {
                        console.log("FAIL : ", responseData);
                        for (i = 0; i < responseData.errorList.length; i++) {
                            // responseData.errorList[i].code;
                            // responseData.errorList[i].dafaultMessage;
                            // responseData.errorList[i].field;
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text();
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text(responseData.errorList[i].defaultMessage);
                        }
                    } else if (responseData.status === "OK") {
                        console.log("OK : ", responseData);
                        document.location.href = "/registrationSuccess";
                    }


                }
            }
        });
    });

    $('#btnResetPassword').click(function () {
        var passswords = {};

        passswords["id"] = $("#id").val();
        passswords["uid"] = $("#uid").val();
        passswords["rawPassword"] = $("#rawPassword").val();
        passswords["repeatRawPassword"] = $("#repeatRawPassword").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "resetPassword",
            data: JSON.stringify(passswords),
            dataType: 'json',
            success: function (responseData) {
                if (responseData !== "" || responseData != null) {
                    if (responseData.status === "FAIL") {
                        console.log("FAIL : ", responseData);
                        for (i = 0; i < responseData.errorList.length; i++) {
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text();
                            $("#" + responseData.errorList[i].field).siblings(".errorspan").text(responseData.errorList[i].defaultMessage);
                        }
                    } else if (responseData.status === "OK") {
                        console.log("OK : ", responseData);
                        document.location.href = "/resetPasswordSuccess";
                    }
                }
            }
        });
    });


});

