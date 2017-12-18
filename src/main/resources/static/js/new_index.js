var TOKEN_KEY = "jwtToken"
var $notLoggedIn = $("#notLoggedIn");
var $loggedIn = $("#loggedIn").hide();
var $loggedInBody = $("#loggedInBody");
var $response = $("#response");
var $login = $("#login");
var $userInfo = $("#userInfo").hide();

function createAuthorizationTokenHeader() {
    var token = getJwtToken();
    if (token) {
        return {"Authorization": "Bearer " + token};
    } else {
        return {};
    }
}

function doLogin(loginData) {
    $.ajax({
        url: "/auth",
        type: "POST",
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            setJwtToken(data.token);

            $.ajax({
                url: "/driverMap",
                type: "GET",
                contentType: "application/json; charset=utf-8",
                headers: createAuthorizationTokenHeader(),
                success: function (data, textStatus, jqXHR) {
                    alert(data);
                    alert(textStatus);
                    alert(jqXHR);

                    $('body').html(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    // alert(jqXHR);
                    // alert(textStatus);
                    // alert(errorThrown);
                    if (401 === 401) {

                    }
                }
            });

            // alert(jqXHR.getAllResponseHeaders());
            // jqXHR.setRequestHeader(createAuthorizationTokenHeader());
            // alert(jqXHR.getAllResponseHeaders());

            // $.ajax({
            //     url: "/driverMap",
            //     type: 'GET',
            //     // Fetch the stored token from localStorage and set in the header
            //     headers: createAuthorizationTokenHeader()
            // });


            // xhr.setRequestHeader("custom_header", "value");
            //jqXHR.setRequestHeader(createAuthorizationTokenHeader());
            //jqXHR.href("/driverMap")


            // alert(createAuthorizationTokenHeader());
            // jqXHR.setRequestHeader(createAuthorizationTokenHeader());
            // requests.push(xhr)
            // jqXHR.href("/driverMap.html");


            //window.location.href = "/driverMap.html";
            // $.ajax({
            //     url: "/driverMap",
            //     type: 'GET',
            //     headers: createAuthorizationTokenHeader()
            // });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status);
            if (jqXHR.status === 401) {
                // $('#loginErrorModal')
                //     .modal("show")
                //     .find(".modal-body")
                //     .empty()
                //     .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
            } else {
                throw new Error("an unexpected error occured: " + errorThrown);
            }
        }
    });
    // $.ajax({
    //     url: "/driverMap",
    //     type: 'GET',
    //     dataType: "html",
    //     headers: createAuthorizationTokenHeader(),
    //     success: function (data) {
    //         $('#wtf').html($(data).text());
    //     }
    // });
}

function requestAfterLogin() {

    $.ajax({
        url: "/driverMap.html",
        type: 'GET',
        // Fetch the stored token from localStorage and set in the header
        headers: createAuthorizationTokenHeader()
    });

    // $.ajax({
    //     url: "/driverMap",
    //     type: "GET",
    //     // contentType: "application/json; charset=utf-8",
    //     // dataType: "json",
    //     headers: createAuthorizationTokenHeader(),
    //     success: function (data, textStatus, jqXHR) {
    //         //window.location.href = "/driverMap";
    //         //window.location.replace("/driverMap");
    //     },
    //     error: function (jqXHR, textStatus, errorThrown) {
    //         if (jqXHR.status === 401) {
    //             $('#loginErrorModal')
    //                 .modal("show")
    //                 .find(".modal-body")
    //                 .empty()
    //                 .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
    //         } else {
    //             throw new Error("an unexpected error occured: " + errorThrown);
    //         }
    //     }
    // });
}


function getJwtToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

function removeJwtToken() {
    localStorage.removeItem(TOKEN_KEY);
}


$(document).ready(function () {


    $('#test').click(function (event) {
        $.ajax({
            url: "/driverMap",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data) {
                $('body').html(data);
            }
        });
    });


    $('#form-signin').submit(function (event) {
        event.preventDefault();
        console.log("Submit");
        var $form = $(this);
        var formData = {
            username: $form.find('input[name="email"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
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

