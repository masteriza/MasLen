function initMap() {
    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), options);
    var input = (document.getElementById('address-input'));

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);

    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete.addListener('place_changed', function () {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(12);  // Why 17? Because it looks good.
        }
        marker.setIcon(/** @type {google.maps.Icon} */({
            //url: place.icon,
            //size: new google.maps.Size(71, 71),
            //origin: new google.maps.Point(0, 0),
            //anchor: new google.maps.Point(17, 34),
            //scaledSize: new google.maps.Size(35, 35)
        }));


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

        infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        infowindow.open(map, marker);
    });

    //Destination point
    var input_destination = (document.getElementById('address-destination-input'));
    var autocomplete_destination = new google.maps.places.Autocomplete(input_destination);
    autocomplete_destination.bindTo('bounds', map);

    var infowindow_destination = new google.maps.InfoWindow();
    var marker_destination = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete_destination.addListener('place_changed', function () {
        infowindow_destination.close();
        marker_destination.setVisible(false);
        var place_destination = autocomplete_destination.getPlace();
        if (!place_destination.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place_destination.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place_destination.geometry.viewport) {
            map.fitBounds(place_destination.geometry.viewport);
        } else {
            map.setCenter(place_destination.geometry.location);
            map.setZoom(12);  // Why 17? Because it looks good.
        }
        marker_destination.setIcon(/** @type {google.maps.Icon} */({
            url: place_destination.icon,
            size: new google.maps.Size(71, 71),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(35, 35)
        }));
        marker_destination.setPosition(place_destination.geometry.location);
        marker_destination.setVisible(true);

        var address_destination = '';
        if (place_destination.address_components) {
            address_destination = [
                (place_destination.address_components[0] && place_destination.address_components[0].short_name || ''),
                (place_destination.address_components[1] && place_destination.address_components[1].short_name || ''),
                (place_destination.address_components[2] && place_destination.address_components[2].short_name || '')
            ].join(' ');
        }

        infowindow_destination.setContent('<div><strong>' + place_destination.name + '</strong><br>' + address_destination);
        infowindow_destination.open(map, marker_destination);

        //Trip route
        var rendererOptions = {map: map};
        var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        var point1 = new google.maps.LatLng(50.4591806, 30.618338600000016);
        var point2 = new google.maps.LatLng(50.4582408, 30.591287999999963);

        var wps = [{location: point1}, {location: point2}];


        var org = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());
        var dest = new google.maps.LatLng(marker_destination.getPosition().lat(), marker_destination.getPosition().lng());
        var request = {
            origin: org,
            destination: dest,
            waypoints: wps,
            travelMode: google.maps.DirectionsTravelMode.DRIVING
            // travelMode: google.maps.TravelMode.DRIVING
        };
        //var directionsService = new google.maps.DirectionsService();
        //var directionsService = new google.maps.DirectionsService;
        var directionsService = new google.maps.DirectionsService();
        // alert(marker.getPosition().lat());
        // alert(marker.position);
        // alert(org);
        // alert(request);


        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            }
            else
                alert('failed to get directions');
        });


        // directionsDisplay = new google.maps.DirectionsRenderer();
        // var request = {
        //
        //     // origin: new google.maps.LatLng(60.023539414725356, 30.283663272857666), //точка старта
        //     // destination: new google.maps.LatLng(59.79530896374892, 30.410317182540894), //точка финиша
        //     origin: new google.maps.LatLng(marker.position), //точка старта
        //     destination: new google.maps.LatLng(marker_destination.position), //точка финиша
        //
        //     travelMode: google.maps.DirectionsTravelMode.DRIVING //режим прокладки маршрута
        // };
        //
        // directionsService.route(request, function(response, status) {
        //     if (status == google.maps.DirectionsStatus.OK) {
        //         directionsDisplay.setDirections(response);
        //     }
        // });
        //
        // directionsDisplay.setMap(map);

        // alert(marker_destination.latitude);
        // alert(marker_destination.position);

        // directionsDisplay.setMap(map);
        // directionsDisplay.setPanel(document.getElementById('panel'));
        //
        // var request = {
        //     origin: 'Chicago',
        //     destination: 'New York',
        //     travelMode: google.maps.DirectionsTravelMode.DRIVING
        // };
        //
        // directionsService.route(request, function (response, status) {
        //     if (status == google.maps.DirectionsStatus.OK) {
        //         directionsDisplay.setDirections(response);
        //     }
        // });


    });


}


