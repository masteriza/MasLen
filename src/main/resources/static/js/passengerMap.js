var markers = [];
var circleEndMarker;
var circleStartMarker;
var passengerStartMarker;
var passengerEndMarker;
var map;
var geoCoder;
var directionsDisplay;

var requestArray = [], renderArray = [];

var directionsService;

// 10 Standard Colours for navigation polylines
var colorArray = [
    'blue',
    'yellow',
    'green',
    'red',
    'purple',
    'orange',
    'black',
    'white',
    'brown',
    'gray'
];
var colorMarker = [
    'http://labs.google.com/ridefinder/images/mm_20_blue.png',
    'http://labs.google.com/ridefinder/images/mm_20_yellow.png',
    'http://labs.google.com/ridefinder/images/mm_20_green.png',
    'http://labs.google.com/ridefinder/images/mm_20_red.png',
    'http://labs.google.com/ridefinder/images/mm_20_purple.png',
    'http://labs.google.com/ridefinder/images/mm_20_orange.png',
    'http://labs.google.com/ridefinder/images/mm_20_black.png',
    'http://labs.google.com/ridefinder/images/mm_20_white.png',
    'http://labs.google.com/ridefinder/images/mm_20_brown.png',
    'http://labs.google.com/ridefinder/images/mm_20_gray.png'
];


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

function initMap() {
    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 12,  //8
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

function initialize() {

}


$(document).ready(function () {
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

    $('body').on('click', '#searchRoute', function () {
        // alert(passengerEndMarker.getPosition().lat() + '    ' + passengerEndMarker.getPosition().lng());
        var PassengerPoints = {
            "startRouteMarkerLatitude": passengerStartMarker.getPosition().lat(),
            "startRouteMarkerLongitude": passengerStartMarker.getPosition().lng(),
            "endRouteMarkerLatitude": passengerEndMarker.getPosition().lat(),
            "endRouteMarkerLongitude": passengerEndMarker.getPosition().lng()
        };

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

                if (sessionStorage) {
                    try {
                        sessionStorage.setItem('SEARCHED_ROUTE', JSON.stringify(routes));
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

                $("#searched_routes").empty();

                for (var i = 0; i < routes.length; i++) {
                    $('#searched_routes').append('' +
                        '<div class="unit_route"></div> ' +
                        '<div class="checkbox_route"><input id="' + routes[i].routeId + '" type="checkbox" checked></div> ' +
                        '<div id="' + routes[i].routeId + '" ' + 'class="route">' + 'ID Route - ' + routes[i].routeId + ' - ' + routes[i].userId + '</div>' +
                        '<div id="' + routes[i].routeId + '" ' + 'class="edit_route"> Edit </div>' +
                        '<div id="' + routes[i].routeId + '" ' + 'class="del_route"> X </div>' +
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

    $('body').on('click', '#showSelectedRoutes', function () {
        directionsService = new google.maps.DirectionsService();
        var routes = JSON.parse(sessionStorage.getItem("SEARCHED_ROUTE"));

        if (directionsDisplay != null) {
            directionsDisplay.setMap(null);
        }

        $.each(routes, function (i, v) {

            org = new google.maps.LatLng(v.routePoints[0].latitude, v.routePoints[0].longitude);
            new google.maps.Marker({
                icon: colorMarker[i],
                map: map,
                position: org
            });

            dest = new google.maps.LatLng(v.routePoints[v.routePoints.length - 1].latitude, v.routePoints[v.routePoints.length - 1].longitude);
            new google.maps.Marker({
                icon: colorMarker[i],
                map: map,
                position: dest
            });

            wps = [];
            for (var i = 1; i < v.routePoints.length - 1; i++) {
                wps.push({location: new google.maps.LatLng(v.routePoints[i].latitude, v.routePoints[i].longitude)});
            }

            var request = {
                origin: org,
                destination: dest,
                waypoints: wps,
                provideRouteAlternatives: true,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };

            requestArray.push({"route": i, "request": request});

        });
        processRequests();
    });

    function processRequests() {

        // Counter to track request submission and process one at a time;
        var i = 0;

        // Used to submit the request 'i'
        function submitRequest() {
            directionsService.route(requestArray[i].request, directionResults);
        }

        // Used as callback for the above request for current 'i'
        function directionResults(result, status) {
            if (status == google.maps.DirectionsStatus.OK) {

                // Create a unique DirectionsRenderer 'i'
                renderArray[i] = new google.maps.DirectionsRenderer();
                renderArray[i].setMap(map);

                // Some unique options from the colorArray so we can see the routes
                renderArray[i].setOptions({
                    preserveViewport: false,
                    suppressInfoWindows: true,
                    suppressMarkers: true,
                    polylineOptions: {
                        strokeWeight: 4,
                        strokeOpacity: 0.5,
                        strokeColor: colorArray[i]
                    }
                    //,
                    //draggable: true,
                    // markerOptions: {
                    //     icon: {
                    //         path: google.maps.SymbolPath.CIRCLE,
                    //         scale: 1,
                    //         strokeColor: colorArray[i]
                    //     }
                    // }
                });

                // Use this new renderer with the result
                renderArray[i].setDirections(result);
                // and start the next request
                nextRequest();
            }

        }

        function nextRequest() {
            // Increase the counter
            i++;
            // Make sure we are still waiting for a request
            if (i >= requestArray.length) {
                // No more to do
                return;
            }
            // Submit another request
            submitRequest();
        }

        // This request is just to kick start the whole process
        submitRequest();
    }

});