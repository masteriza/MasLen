var map;
var startRouteMarker;

var finishRouteMarker;
var org;
var dest;

var directionsDisplay;

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
            //routeTrip();
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

        finishRouteMarker.setVisible(false);

        var place_destination = autocomplete_destination.getPlace();
        if (!place_destination.geometry) {
            window.alert("No details available for input: '" + place_destination.name + "'");
            return;
        }

        if (place_destination.geometry.viewport) {
            map.fitBounds(place_destination.geometry.viewport);
        } else {
            map.setCenter(place_destination.geometry.location);
            map.setZoom(12);
        }

        finishRouteMarker.setPosition(place_destination.geometry.location);
        finishRouteMarker.setVisible(true);


        if (marker != null && finishRouteMarker != null) {
            //routeTrip();
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
$(document).ready(function () {


    $('#save').on('click', function () {
        var driver = {
            "driverId": 0,
            "userId": 0,
            "startRouteLatitude": directionsDisplay.directions.routes[0].legs[0].start_location.lat(),
            "startRouteLongitude": directionsDisplay.directions.routes[0].legs[0].start_location.lng(),
            "finishRouteLatitude": directionsDisplay.directions.routes[0].legs[0].end_location.lat(),
            "finishRouteLongitude": directionsDisplay.directions.routes[0].legs[0].end_location.lng()
        }

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: "saveDriverRoute",
            data: JSON.stringify(driver),
            success: function (responseData) {
            }
        });
    });
});
