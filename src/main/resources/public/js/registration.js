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

        if (( form.genderRadios[0].checked == false ) && ( form.genderRadios[1].checked == false )) {
            // alert("Please choose your Gender: Male or Female");
            user["gender"] = "X";

        } else {
            user["gender"] = $('input[name=genderRadios]:checked').val();
        }
        //user["gender"] = $('input[name=genderRadios]:checked').val();
        user["agree"] = document.getElementById("agree").checked;
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

