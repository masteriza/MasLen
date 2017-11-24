var markers = [];
var circleEndMarker;
var circleStartMarker;
var passengerStartMarker;
var passengerEndMarker;
var map;
var geoCoder;

function initMap() {
    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("map_canvas"), options);

    markers = [];

    geoCoder = new google.maps.Geocoder();//Определение геокодера
    passengerStartMarker = new google.maps.Marker({ //определение маркера
        map: map,
        icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
        draggable: true
    });

    passengerEndMarker = new google.maps.Marker({ //определение маркера
        map: map,
        icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
        draggable: true
    });

    circleStartMarker = new google.maps.Circle({
        map: map,
        radius: 1000,    // 10 miles in metres
        fillColor: '#00FF00'
    });

    circleEndMarker = new google.maps.Circle({
        map: map,
        radius: 1000,    // 10 miles in metres
        fillColor: '#AA0000'
    });

    google.maps.event.addListener(passengerStartMarker, 'dragend', function () {
        geoCoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('#address-input').val(results[0].formatted_address);
                }
            }
        });
    });

    google.maps.event.addListener(passengerEndMarker, 'dragend', function () {
        geoCoder.geocode({'latLng': passengerEndMarker.getPosition()}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('#address-destination-input').val(results[0].formatted_address);
                }
            }
        });
    });

    google.maps.event.addListener(map, 'click', function (event) {
        if (markers.length == 1) {
            markers.push(passengerEndMarker);
            passengerEndMarker.setPosition(event.latLng);
            geoCoder.geocode({'latLng': passengerEndMarker.getPosition()}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        $('#address-destination-input').val(results[0].formatted_address);
                    }
                }
            });
            circleEndMarker.bindTo('center', passengerEndMarker, 'position');
        }

        if (markers.length == 0) {
            markers.push(passengerStartMarker);
            passengerStartMarker.setPosition(event.latLng);
            geoCoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        $('#address-input').val(results[0].formatted_address);
                    }
                }
            });
            circleStartMarker.bindTo('center', passengerStartMarker, 'position');
        }
    });

}

function initialize() {   //Определение карты

    // var latlng = new google.maps.LatLng(50.4501, 30.5234);
    // var options = {
    //     zoom: 8,
    //     center: latlng,
    //     mapTypeId: google.maps.MapTypeId.ROADMAP
    // };
    //
    // map = new google.maps.Map(document.getElementById("map_canvas"), options);

    // markers = [];
    //
    // geoCoder = new google.maps.Geocoder();//Определение геокодера
    // passengerStartMarker = new google.maps.Marker({ //определение маркера
    //     map: map,
    //     icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
    //     draggable: true
    // });
    //
    // passengerEndMarker = new google.maps.Marker({ //определение маркера
    //     map: map,
    //     icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
    //     draggable: true
    // });
    //
    // circleStartMarker = new google.maps.Circle({
    //     map: map,
    //     radius: 1000,    // 10 miles in metres
    //     fillColor: '#00FF00'
    // });
    //
    // circleEndMarker = new google.maps.Circle({
    //     map: map,
    //     radius: 1000,    // 10 miles in metres
    //     fillColor: '#AA0000'
    // });

}


$(document).ready(function () {

    initialize();


    // google.maps.event.addListener(passengerStartMarker, 'dragend', function () {
    //     geoCoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
    //         if (status == google.maps.GeocoderStatus.OK) {
    //             if (results[0]) {
    //                 $('#address-input').val(results[0].formatted_address);
    //             }
    //         }
    //     });
    // });
    //
    // google.maps.event.addListener(passengerEndMarker, 'dragend', function () {
    //     geoCoder.geocode({'latLng': passengerEndMarker.getPosition()}, function (results, status) {
    //         if (status == google.maps.GeocoderStatus.OK) {
    //             if (results[0]) {
    //                 $('#address-destination-input').val(results[0].formatted_address);
    //             }
    //         }
    //     });
    // });
    //
    // google.maps.event.addListener(map, 'click', function (event) {
    //     if (markers.length == 1) {
    //         markers.push(passengerEndMarker);
    //         passengerEndMarker.setPosition(event.latLng);
    //         geoCoder.geocode({'latLng': passengerEndMarker.getPosition()}, function (results, status) {
    //             if (status == google.maps.GeocoderStatus.OK) {
    //                 if (results[0]) {
    //                     $('#address-destination-input').val(results[0].formatted_address);
    //                 }
    //             }
    //         });
    //         circleEndMarker.bindTo('center', passengerEndMarker, 'position');
    //     }
    //
    //     if (markers.length == 0) {
    //         markers.push(passengerStartMarker);
    //         passengerStartMarker.setPosition(event.latLng);
    //         geoCoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
    //             if (status == google.maps.GeocoderStatus.OK) {
    //                 if (results[0]) {
    //                     $('#address-input').val(results[0].formatted_address);
    //                 }
    //             }
    //         });
    //         circleStartMarker.bindTo('center', passengerStartMarker, 'position');
    //     }
    // });


    $("#address-input").autocomplete({
        //Определяем значение для адреса при геокодировании
        source: function (request, response) {
            geoCoder.geocode({'address': request.term}, function (results, status) {
                response($.map(results, function (item) {
                    return {
                        label: item.formatted_address,
                        value: item.formatted_address,
                        latitude: item.geometry.location.lat(),
                        longitude: item.geometry.location.lng()
                    }
                }));
            })
        },
        //Выполняется при выборе конкретного адреса
        select: function (event, ui) {
            //$("#latitude").val(ui.item.latitude);
            //$("#longitude").val(ui.item.longitude);
            var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
            passengerStartMarker.setPosition(location);
            map.setCenter(location);
        }
    }); //Добавляем слушателя события обратного геокодирования для маркера при его перемещении


    $("#address-destination-input").autocomplete({
        //Определяем значение для адреса при геокодировании
        source: function (request, response) {
            geoCoder.geocode({'address': request.term}, function (results, status) {
                response($.map(results, function (item) {
                    return {
                        label: item.formatted_address,
                        value: item.formatted_address,
                        latitude: item.geometry.location.lat(),
                        longitude: item.geometry.location.lng()
                    }
                }));
            })
        },
        //Выполняется при выборе конкретного адреса
        select: function (event, ui) {
            var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
            passengerEndMarker.setPosition(location);
            map.setCenter(location);
        }
    });

    // function PassengerPoints() {
    //     this.startRouteMarkerLatitude = 0;
    //     this.startRouteMarkerLongitude = 0;
    //     this.endRouteMarkerLatitude = 0;
    //     this.endRouteMarkerLongitude = 0;
    // }


    $('body').on('click', '#searchRoute', function () {
        alert(passengerEndMarker.getPosition().lat() + '    ' + passengerEndMarker.getPosition().lng());
        var PassengerPoints = {
            "startRouteMarkerLatitude": passengerStartMarker.getPosition().lat(),
            "startRouteMarkerLongitude": passengerStartMarker.getPosition().lng(),
            "endRouteMarkerLatitude": passengerEndMarker.getPosition().lat(),
            "endRouteMarkerLongitude": passengerEndMarker.getPosition().lng()
        };

        // PassengerPoints.startRouteMarkerLatitude = passengerStartMarker.getPosition().lat();
        // PassengerPoints.startRouteMarkerLongitude = passengerStartMarker.getPosition().lng();
        // PassengerPoints.endRouteMarkerLatitude = passengerEndMarker.getPosition().lat();
        // PassengerPoints.endRouteMarkerLongitude = passengerEndMarker.getPosition().lng();


        var routeId = $(this).attr('id');

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: "searchRoute",
            data: JSON.stringify(PassengerPoints),
            dataType: 'json',
            timeout: 100000,
            success: function (responseData) {
                console.dir(responseData);
                var routes = [];
                for (var i = 0; i < responseData.result.length; i++) {
                    var route = new Route();
                    route.routeId = responseData.result[i].routeId;
                    route.userId = responseData.result[i].userId;
                    route.startRouteLatitude = responseData.result[i].startRouteLatitude;
                    route.startRouteLongitude = responseData.result[i].startRouteLongitude;
                    route.finishRouteLatitude = responseData.result[i].finishRouteLatitude;
                    route.finishRouteLongitude = responseData.result[i].finishRouteLongitude;
                    route.routePoints = [];

                    for (var j = 0; j < responseData.result[i].routePoints.length; j++) {
                        var routePoint = new RoutePoint();
                        routePoint.pointId = responseData.result[i].routePoints[j].pointId;
                        routePoint.indexPoint = responseData.result[i].routePoints[j].indexPoint;
                        routePoint.latitude = responseData.result[i].routePoints[j].latitude;
                        routePoint.longitude = responseData.result[i].routePoints[j].longitude;
                        route.routePoints.push(routePoint);
                    }
                    routes.push(route);
                }
                var routes = [];


            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }

        });
    });
});