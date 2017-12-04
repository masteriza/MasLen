$(document).ready(function () {

    $('#btnRegistration').click(function () {

        $('.errorspan').each(function () {
            $(this).text("");
        });

        var birthday_Day = $("#birthday_Day option:selected").val();
        var birthday_Month = $("#birthday_Month option:selected").val();
        var birthday_Year = $("#birthday_Year option:selected").val();

        var user = {};
        //user["userId"] = 0;
        user["lastName"] = $("#lastName").val();
        user["firstName"] = $("#firstName").val();
        user["middleName"] = $("#middleName").val();
        user["birthday"] = new Date(Date.UTC(birthday_Year, birthday_Month, birthday_Day)).toISOString().substring(0, 10);
        user["email"] = $("#email").val();
        user["rawPassword"] = $("#rawPassword").val();
        user["repeatRawPassword"] = $("#repeatRawPassword").val();
        user["phone"] = $('#phone').val();
        if ($('input[name=genderRadios]:checked').val() == undefined) {
            user["gender"] = "X";
        } else {
            user["gender"] = $('input[name=genderRadios]:checked').val();
        }
        user["agree"] = document.getElementById("agree").checked;
        // user["status"] = '';

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "user",
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (responseData) {
                if (responseData != "" || responseData != null) {
                    for (i = 0; i < responseData.errorList.length; i++) {
                        // responseData.errorList[i].code;
                        // responseData.errorList[i].dafaultMessage;
                        // responseData.errorList[i].field;
                        $("#" + responseData.errorList[i].field).siblings(".errorspan").text();
                        $("#" + responseData.errorList[i].field).siblings(".errorspan").text(responseData.errorList[i].defaultMessage);
                    }
                }
                //alert($("#lastName").siblings("span").text());

//                $("#lastName").siblings("span").text("zzzzzzzzzzzzz");

//                alert($("#lastName").siblings("span").text());
                //$("#tagscloud span").text("Your text here");

                // if (responseData != "") {
                //     $(".errorSummary").empty().append(responseData);
                // } else {
                //     location.href = 'message.jsp';
                // }

                console.log("SUCCESS : ", responseData);
                //:todo сделать переход на страницу успешной регистрации
                //window.location.replace("/driverMap");
            }
        });
    });
});

