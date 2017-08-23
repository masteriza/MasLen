$(document).ready(function () {

    $('#btnRegistration').click(function () {

        var birthday_Day = $("#birthday_Day option:selected").val();
        var birthday_Month = $("#birthday_Month option:selected").val();
        var birthday_Year = $("#birthday_Year option:selected").val();


        var user = {};
        user["userId"] = 0;
        user["lastName"] = $("#lastName").val();
        user["firstName"] = $("#firstName").val();
        user["middleName"] = $("#middleName").val();
        user["birthday"] = new Date(Date.UTC(birthday_Year, birthday_Month, birthday_Day));
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
        user["status"] = '';

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "user",
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (responseData) {
                for (i = 0; i < responseData.errorList.length; i++) {
                    responseData.errorList[i].code;
                    responseData.errorList[i].field;
                }

                alert($(".sss").find("span").text());

                alert($("#lastName").siblings("span").html());
                //$("#tagscloud span").text("Your text here");

                if (responseData != "") {
                    $(".errorSummary").empty().append(responseData);
                } else {
                    location.href = 'message.jsp';
                }
            }
        });
    });
});

