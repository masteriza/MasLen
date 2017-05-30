var map;
var marker;
var finishRouteMarker;
var org;
var dest;

var directionsDisplay;

function initMap() {
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

    var infowindow = new google.maps.InfoWindow();

    autocomplete.addListener('place_changed', function () {
        marker = new google.maps.Marker({
            map: map
        });
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(12);  // Why 17? Because it looks good.
        }

        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        var address = '';
        if (place.address_components) {
            address = [
                (place.address_components[0] && place.address_components[0].short_name || ''),
                (place.address_components[1] && place.address_components[1].short_name || ''),
                (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }

        //infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        //infowindow.open(map, startRouteMarker);

        if (marker != null && finishRouteMarker != null) {
            routeTrip();
        }
    });

    //Destination point
    var input_destination = (document.getElementById('address-destination-input'));
    var autocomplete_destination = new google.maps.places.Autocomplete(input_destination);
    autocomplete_destination.bindTo('bounds', map);

    var infowindow_destination = new google.maps.InfoWindow();

    autocomplete_destination.addListener('place_changed', function () {
        finishRouteMarker = new google.maps.Marker({
            map: map
        });
        infowindow_destination.close();
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

        var address_destination = '';
        if (place_destination.address_components) {
            address_destination = [
                (place_destination.address_components[0] && place_destination.address_components[0].short_name || ''),
                (place_destination.address_components[1] && place_destination.address_components[1].short_name || ''),
                (place_destination.address_components[2] && place_destination.address_components[2].short_name || '')
            ].join(' ');
        }

        //infowindow_destination.setContent('<div><strong>' + place_destination.name + '</strong><br>' + address_destination);
        //infowindow_destination.open(map, finishRouteMarker);

        if (marker != null && finishRouteMarker != null) {
            routeTrip();
        }
    });


    // google.maps.event.addListener(startRouteMarker, 'dragend', function () {
    //     //geocodePosition(startRouteMarker.getPosition());
    //     routeTrip();
    // });
}

function routeTrip() {
    if (directionsDisplay != null) {
        directionsDisplay.setMap(null);
    }

    //Trip route
    var rendererOptions = {map: map, draggable: true};
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);

    directionsDisplay.addListener('directions_changed', function () {
        alert(directionsDisplay.directions.routes[0].legs[0].end_location.lat());
        //alert(startRouteMarker.getPosition().lat());
        // computeTotalDistance(directionsDisplay.getDirections());
    });

    var point1 = new google.maps.LatLng(50.4591806, 30.618338600000016);
    var point2 = new google.maps.LatLng(50.4582408, 30.591287999999963);

    var wps = [{location: point1}, {location: point2}];
    wps = null;

    org = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());
    dest = new google.maps.LatLng(finishRouteMarker.getPosition().lat(), finishRouteMarker.getPosition().lng());
    var request = {
        origin: org,
        destination: dest,
        waypoints: wps,
        provideRouteAlternatives: true,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };

    var directionsService = new google.maps.DirectionsService();
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
        else
            alert('failed to get directions');
    });
    marker.setVisible(false);
    finishRouteMarker.setVisible(false);

    alert(org);


    // startRouteMarker = null;
    // finishRouteMarker = null;
}
//JSON.stringify(data),
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
            //     {
            //     startRouteLatitude: directionsDisplay.directions.routes[0].legs[0].start_location.lat(),
            //     startRouteLongitude: directionsDisplay.directions.routes[0].legs[0].start_location.lng(),
            //
            //     finishRouteLatitude: directionsDisplay.directions.routes[0].legs[0].end_location.lat(),
            //     finishRouteLongitude: directionsDisplay.directions.routes[0].legs[0].end_location.lng()
            // },
            success: function (responseData) {
                // if (responseData != "") {
                //     location.href = 'login.jsp';
                // } else {
                //     location.href = 'sec/useraccount.jsp';
                // }
            }
        });

        //alert(directionsDisplay.directions.routes[0].legs[directionsDisplay.directions.routes[0].legs.length - 1].end_location.lat());
    });
});
