var polylines = [];
var polylineOptions = {};
var map;
var infowindow;

function initialize() {

    var center = new google.maps.LatLng(0, 0);
    var myOptions = {
        zoom: 7,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: center
    }

    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    var start = "50.443886, 30.445449";
    var end = "50.460841, 30.619999";

    plotDirections(start, end);
}

function plotDirections(start, end) {

    var method = 'DRIVING';

    var request = {
        origin: start,
        destination: end,
        travelMode: google.maps.DirectionsTravelMode[method],
        provideRouteAlternatives: true
    };

    infowindow = new google.maps.InfoWindow();

    var directionsService = new google.maps.DirectionsService();
    directionsService.route(request, function (response, status) {

        if (status == google.maps.DirectionsStatus.OK) {

            var routes = response.routes;
            var colors = ['red', 'green', 'blue', 'orange', 'yellow', 'black'];
            var directionsDisplays = [];

            // Reset the start and end variables to the actual coordinates
            start = response.routes[0].legs[0].start_location;
            end = response.routes[0].legs[0].end_location;

            ///123
            renderDirectionsPolylines(response);


            //123

            // Loop through each route
            //for (var i = 0; i < routes.length; i++) {
            for (var i = 0; i < 1; i++) {

                var directionsDisplay = new google.maps.DirectionsRenderer({
                    map: map,
                    directions: response,
                    routeIndex: i,
                    draggable: true,
                    suppressPolylines: false,
                    infoWindow: infowindow,
                    polylineOptions: {

                        strokeColor: colors[i],
                        strokeWeight: 4,
                        strokeOpacity: .3
                    }
                });

                // Push the current renderer to an array
                directionsDisplays.push(directionsDisplay);

                // Listen for the directions_changed event for each route
                google.maps.event.addListener(directionsDisplay, 'directions_changed', (function (directionsDisplay, i) {

                    return function () {

                        var directions = directionsDisplay.getDirections();
                        var new_start = directions.routes[0].legs[0].start_location;
                        var new_end = directions.routes[0].legs[0].end_location;

                        if ((new_start.toString() !== start.toString()) || (new_end.toString() !== end.toString())) {

                            // Remove every route from map
                            for (var j = 0; j < directionsDisplays.length; j++) {

                                directionsDisplays[j].setMap(null);
                            }

                            // Redraw routes with new start/end coordinates
                            plotDirections(new_start, new_end);
                        }
                    }
                })(directionsDisplay, i)); // End listener
            } // End route loop
        }
    });
}

function renderDirectionsPolylines(response) {
    for (var i = 0; i < polylines.length; i++) {
        polylines[i].setMap(null);
    }
    var legs = response.routes[0].legs;
    for (i = 0; i < legs.length; i++) {
        var steps = legs[i].steps;
        for (j = 0; j < steps.length; j++) {
            var nextSegment = steps[j].path;
            var stepPolyline = new google.maps.Polyline(polylineOptions);
            for (k = 0; k < nextSegment.length; k++) {
                stepPolyline.getPath().push(nextSegment[k]);
            }
            stepPolyline.setMap(map);
            polylines.push(stepPolyline);
            google.maps.event.addListener(stepPolyline, 'click', function (evt) {
                infowindow.setContent("you clicked on the route<br>" + evt.latLng.toUrlValue(6));
                infowindow.setPosition(evt.latLng);
                infowindow.open(map);
            })
        }
    }
}

$(document).ready(function () {
    initialize();
});