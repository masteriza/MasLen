var map;
var startRouteMarker;

var finishRouteMarker;
var org;
var dest;

var directionsDisplay;

var directionsService;

var wps = [];

function initMap() {
    var geocoder = new google.maps.Geocoder();

    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), options);
    var input = (document.getElementById('address-input'));

    //google.maps.event.addDomListener(document.getElementById('routebtn'), 'click', calcRoute);
    // google.maps.event.addDomListener($('#driver_routes').on('click', '.route', addRouteOnMap));
    google.maps.event.addDomListener($('#driver_routes').on('click', '.route', function () {
        addRouteOnMap(this.id);
    }));

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);

    autocomplete.addListener('place_changed', function () {
        if (!startRouteMarker) {
            startRouteMarker = new google.maps.Marker({
                map: map,
                draggable: true
            });
        }
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);

        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }

        startRouteMarker.setPosition(place.geometry.location);
        startRouteMarker.setVisible(true);

        if (startRouteMarker != null && finishRouteMarker != null) {
            routeTrip();
        }

        google.maps.event.addListener(startRouteMarker, 'dragend', function () {
            geocoder.geocode({'latLng': startRouteMarker.getPosition()}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        $('#address-input').val(results[0].formatted_address);
                    }
                }
            });
        });
    });


    // google.maps.event.addListener(startRouteMarker, 'dragend', function () {
    //     geocoder.geocode({'latLng': startRouteMarker.getPosition()}, function (results, status) {
    //         if (status == google.maps.GeocoderStatus.OK) {
    //             if (results[0]) {
    //                 $('#address-input').val(results[0].formatted_address);
    //             }
    //         }
    //     });
    // });

    //Destination point
    var input_destination = (document.getElementById('address-destination-input'));

    var autocomplete_destination = new google.maps.places.Autocomplete(input_destination);
    autocomplete_destination.bindTo('bounds', map);

    autocomplete_destination.addListener('place_changed', function () {
        finishRouteMarker = new google.maps.Marker({
            map: map,
            draggable: true
        });

        var place_destination = autocomplete_destination.getPlace();
        if (!place_destination.geometry) {
            window.alert("No details available for input: '" + place_destination.name + "'");
            return;
        }

        if (place_destination.geometry.viewport) {
            map.fitBounds(place_destination.geometry.viewport);
        } else {
            map.setCenter(place_destination.geometry.location);
            map.setZoom(17);
        }

        finishRouteMarker.setPosition(place_destination.geometry.location);
        finishRouteMarker.setVisible(true);


        if (startRouteMarker != null && finishRouteMarker != null) {
            routeTrip();
        }

        google.maps.event.addListener(finishRouteMarker, 'dragend', function () {
            geocoder.geocode({'latLng': finishRouteMarker.getPosition()}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        $('#address-destination-input').val(results[0].formatted_address);
                    }
                }
            });
        });
    });
}

function addRouteOnMap(routeId) {
    if (routeId == null) {
        return;
    }
    //var routeId = $(this).attr('id');
    var routez = JSON.parse(sessionStorage.getItem("DRIVER_ROUTE"));

    wps = [];
    $.each(routez, function (i, v) {
        if (v.routeId == routeId) {
            org = new google.maps.LatLng(v.routePoints[0].latitude, v.routePoints[0].longitude);
            dest = new google.maps.LatLng(v.routePoints[v.routePoints.length - 1].latitude, v.routePoints[v.routePoints.length - 1].longitude);
            for (var i = 1; i < v.routePoints.length - 1; i++) {
                wps.push({location: new google.maps.LatLng(v.routePoints[i].latitude, v.routePoints[i].longitude)});
                // if (v.routePoints[i].indexPoint == 0) {
                //     //org = new google.maps.LatLng(v.routePoints[i].latitude, v.routePoints[i].longitude);
                // } else if (v.routePoints[i].indexPoint == v.routePoints.length - 1) {
                //     //dest = new google.maps.LatLng(v.routePoints[i].latitude, v.routePoints[i].longitude);
                // } else {
                //     wps.push({location: new google.maps.LatLng(v.routePoints[i].latitude, v.routePoints[i].longitude)});
                // }
            }
        }
    });

    var i = 0;
    if (directionsDisplay != null) {
        directionsDisplay.setMap(null);
    }

    var rendererOptions = {map: map, draggable: true};
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsDisplay.addListener('directions_changed', function () {
        //alert(directionsDisplay.directions.routes[0].legs[0].end_location.lat());
        //alert(startRouteMarker.getPosition().lat());
        // computeTotalDistance(directionsDisplay.getDirections());
    });

    // org = new google.maps.LatLng(50.4531806, 30.618338600000016);
    // dest = new google.maps.LatLng(50.4582408, 30.591287999999963);
    // org = new google.maps.LatLng(startRouteMarker.getPosition().lat(), startRouteMarker.getPosition().lng());
    // dest = new google.maps.LatLng(finishRouteMarker.getPosition().lat(), finishRouteMarker.getPosition().lng());

    var request = {
        origin: org,
        destination: dest,
        waypoints: wps,
        provideRouteAlternatives: true,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };

    //var directionsService = new google.maps.DirectionsService();
    directionsService = new google.maps.DirectionsService();
    directionsService.route(request, function (response, status) {
        var i = 0;
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
        else
            alert('failed to get directions');
    });
    startRouteMarker.setVisible(false);
    finishRouteMarker.setVisible(false);
}


function routeTrip() {
    if (directionsDisplay != null) {
        directionsDisplay.setMap(null);
    }

    //Trip route
    var rendererOptions = {map: map, draggable: true};
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);

    directionsDisplay.addListener('directions_changed', function () {
        //alert(directionsDisplay.directions.routes[0].legs[0].end_location.lat());
        //alert(startRouteMarker.getPosition().lat());
        // computeTotalDistance(directionsDisplay.getDirections());
    });

    // var point1 = new google.maps.LatLng(50.4591806, 30.618338600000016);
    // var point2 = new google.maps.LatLng(50.4582408, 30.591287999999963);
    //
    // var wps = [{location: point1}, {location: point2}];
    // wps = null;

    org = new google.maps.LatLng(startRouteMarker.getPosition().lat(), startRouteMarker.getPosition().lng());
    dest = new google.maps.LatLng(finishRouteMarker.getPosition().lat(), finishRouteMarker.getPosition().lng());
    var request = {
        origin: org,
        destination: dest,
        waypoints: wps,
        provideRouteAlternatives: true,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };

    // var directionsService = new google.maps.DirectionsService();
    directionsService = new google.maps.DirectionsService();
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
        else
            alert('failed to get directions');
    });
    startRouteMarker.setVisible(false);
    finishRouteMarker.setVisible(false);
}

function routePointz() {
    this.indexPoint = 0;
    this.latitude = 0;
    this.longitude = 0;
}

function Route() {
    this.routeId = 0;
    this.userId = 0;
    this.startRouteLatitude = 0;
    this.startRouteLongitude = 0;
    this.finishRouteLatitude = 0;
    this.finishRouteLongitude = 0;
    this.routePoints = [];
}

function RoutePoint() {
    this.pointId = 0;
    this.indexPoint = 0;
    this.latitude = 0;
    this.longitude = 0;
}


$(document).ready(function () {

    $('#save').on('click', function () {
        var steps = directionsDisplay.directions.routes[0].legs[0].steps;
        var routePoints = [];
        var routePoint = new routePointz();

        routePoint.indexPoint = 0;
        routePoint.latitude = steps[0].start_location.lat();
        routePoint.longitude = steps[0].start_location.lng();
        routePoints.push(routePoint);

        for (var i = 1; i <= steps.length - 1; i++) {
            routePoint = new routePointz();
            routePoint.indexPoint = i;
            routePoint.latitude = steps[i].end_location.lat();
            routePoint.longitude = steps[i].end_location.lng();
            routePoints.push(routePoint);
        }

        var driverRoute = {
            "routeId": 0,
            "userId": 0,
            "startRouteLatitude": directionsDisplay.directions.routes[0].legs[0].start_location.lat(),
            "startRouteLongitude": directionsDisplay.directions.routes[0].legs[0].start_location.lng(),
            "finishRouteLatitude": directionsDisplay.directions.routes[0].legs[0].end_location.lat(),
            "finishRouteLongitude": directionsDisplay.directions.routes[0].legs[0].end_location.lng(),
            "routePoints": routePoints

        };

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: "saveDriverRoute",
            data: JSON.stringify(driverRoute),
            dataType: 'json',
            timeout: 100000,
            success: function (responseData) {
                console.dir(responseData.result);
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

                if (sessionStorage) {
                    try {
                        sessionStorage.setItem('DRIVER_ROUTE', JSON.stringify(routes));
                    } catch (e) {
                        if (e == QUOTA_EXCEEDED_ERR) {
                            alert('Sorry, something went wrong_\n' +
                                'SessionStorage is full\n' +
                                'Let us know about it, we will be grateful to you.');
                            //todo: залогировать это в БД
                        }
                    }
                } else {
                    alert("Sorry, your browser do not support session storage.");
                }

                var routez = JSON.parse(sessionStorage.getItem("DRIVER_ROUTE"));
                $("#driver_routes").empty();

                for (var i = 0; i < routez.length; i++) {
                    // $('#driver_routes').append('' +
                    //     '<div id="' + routez[i].routeId + '" ' + 'class="route">' + 'ID Route' +
                    //     routez[i].routeId + ' - ' + routez[i].userId + '</div>' );

                    $('#driver_routes').append('' +
                        '<div class="unit_route"></div> ' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="route">' + 'ID Route - ' +
                        routez[i].routeId + ' - ' + routez[i].userId + '</div>' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="edit_route"> Edit </div>' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="del_route"> X </div>' +
                        '</div> ');

                    //console.log(routez[i].routeId);
                }


                //alert('dfg');

            },
            error: function (e) {
                console.log("ERROR: ", e);
                display(e);
            },
            done: function (e) {
                console.log("DONE");
            }

        });
        var a = 0;
        //alert(directionsDisplay.directions.routes[0].legs[directionsDisplay.directions.routes[0].legs.length - 1].end_location.lat());
    });

    $('#getAllRoutes').on('click', function () {

    });


    // $('.del_route').on('click', function () {
    $('#driver_routes').on('click', '.del_route', function () {
        alert($(this).attr('id'));

        var routeId = $(this).attr('id');

        $.ajax({
            type: "DELETE",
            contentType: 'application/json; charset=utf-8',
            url: "deleteDriverRoute/" + routeId,
            //data: JSON.stringify(driverRoute),
            dataType: 'json',
            timeout: 100000,
            success: function (responseData) {
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

                if (sessionStorage) {
                    try {
                        sessionStorage.setItem('DRIVER_ROUTE', JSON.stringify(routes));
                    } catch (e) {
                        if (e == QUOTA_EXCEEDED_ERR) {
                            alert('Sorry, something went wrong_\n' +
                                'SessionStorage is full\n' +
                                'Let us know about it, we will be grateful to you.');
                            //todo: залогировать это в БД
                        }
                    }
                } else {
                    alert("Sorry, your browser do not support session storage.");
                }

                var routez = JSON.parse(sessionStorage.getItem("DRIVER_ROUTE"));
                $("#driver_routes").empty();
                for (var i = 0; i < routez.length; i++) {
                    $('#driver_routes').append('' +
                        '<div class="unit_route"></div> ' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="route">' + 'ID Route - ' +
                        routez[i].routeId + ' - ' + routez[i].userId + '</div>' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="edit_route"> Edit </div>' +
                        '<div id="' + routez[i].routeId + '" ' + 'class="del_route"> X </div>' +
                        '</div> ');
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }

        });
    });


    // $('#driver_routes').on('click', '.route', function () {
    //     //alert($(this).attr('id'));
    //     var routeId = $(this).attr('id');
    //     var routez = JSON.parse(sessionStorage.getItem("DRIVER_ROUTE"));
    //     // for (var i = 0; i < routez.length; i++) {
    //     //     routez[i].routeId =
    //     // }
    //     $.each(routez, function (i, v) {
    //         if (v.routeId == routeId) {
    //             alert(v.routeId);
    //             return;
    //         } else {
    //             alert('Sorry, something went wrong.');
    //         }
    //     });
    // });


});



