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
            url: place.icon,
            size: new google.maps.Size(71, 71),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(35, 35)
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

        //alert(marker_destination.position);

        //Trip route
        var rendererOptions = {map: map};
        var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        var point1 = new google.maps.LatLng(50.4591806, 30.618338600000016);
        var point2 = new google.maps.LatLng(50.4582408, 30.591287999999963);

        var wps = [{location: point1}, {location: point2}];


        var org = new google.maps.LatLng(marker.position);
        var dest = new google.maps.LatLng(marker_destination.position);
        var request = {
            origin: org,
            destination: dest,
            //waypoints: wps,
            travelMode: google.maps.DirectionsTravelMode.DRIVING
        };
        //var directionsService = new google.maps.DirectionsService();
        var directionsService = new google.maps.DirectionsService;
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


    // var geocoder;
    // var map;
    // var marker;
    //
    // function initialize() {
    //     var latlng = new google.maps.LatLng(-33.897, 150.099);
    //     var myOptions = {zoom: 9, center: latlng, mapTypeId: google.maps.MapTypeId.TERRAIN};
    //     map = new google.maps.Map(document.getElementById("map"), myOptions);
    //     var rendererOptions = {map: map};
    //     directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    //     var point1 = new google.maps.LatLng(-33.8975098545041, 151.09962701797485);
    //     var point2 = new google.maps.LatLng(-33.8584421519279, 151.0693073272705);
    //     var point3 = new google.maps.LatLng(-33.87312358690301, 151.99952697753906);
    //     var point4 = new google.maps.LatLng(-33.84525521656404, 151.0421848297119);
    //     var wps = [{location: point1}, {location: point2}, {location: point4}];
    //     var org = new google.maps.LatLng(-33.89192157947345, 151.13604068756104);
    //     var dest = new google.maps.LatLng(-33.69727974097957, 150.29047966003418);
    //     var request = {
    //         origin: org,
    //         destination: dest,
    //         waypoints: wps,
    //         travelMode: google.maps.DirectionsTravelMode.DRIVING
    //     };
    //     directionsService = new google.maps.DirectionsService();
    //     directionsService.route(request, function (response, status) {
    //         if (status == google.maps.DirectionsStatus.OK) {
    //             directionsDisplay.setDirections(response);
    //         }
    //         else
    //             alert('failed to get directions');
    //     });
    // }


}

// function initialize() {   //Определение карты
//     var latlng = new google.maps.LatLng(50.4501, 30.5234);
//     var options = {
//         zoom: 8,
//         center: latlng,
//         mapTypeId: google.maps.MapTypeId.ROADMAP
//     };
//     map = new google.maps.Map(document.getElementById("map_canvas"), options);
//     var input = (document.getElementById('address-input'));
//     var autocomplete = new google.maps.places.Autocomplete(input);
//     autocomplete.bindTo('bounds', map);
//     var marker = new google.maps.Marker({
//         map: map,
//         anchorPoint: new google.maps.Point(0, -29)
//     });
//
//     autocomplete.addListener('place_changed', function () {
//         marker.setVisible(false);
//         var place = autocomplete.getPlace();
//         if (!place.geometry) {
//             // User entered the name of a Place that was not suggested and
//             // pressed the Enter key, or the Place Details request failed.
//             window.alert("No details available for input: '" + place.name + "'");
//             return;
//         }
//
//         // If the place has a geometry, then present it on a map.
//         if (place.geometry.viewport) {
//             map.fitBounds(place.geometry.viewport);
//         } else {
//             map.setCenter(place.geometry.location);
//             map.setZoom(17);  // Why 17? Because it looks good.
//         }
//         marker.setIcon(/** @type {google.maps.Icon} */({
//             url: place.icon,
//             size: new google.maps.Size(71, 71),
//             origin: new google.maps.Point(0, 0),
//             anchor: new google.maps.Point(17, 34),
//             scaledSize: new google.maps.Size(35, 35)
//         }));
//         marker.setPosition(place.geometry.location);
//         marker.setVisible(true);
//
//         var address = '';
//         if (place.address_components) {
//             address = [
//                 (place.address_components[0] && place.address_components[0].short_name || ''),
//                 (place.address_components[1] && place.address_components[1].short_name || ''),
//                 (place.address_components[2] && place.address_components[2].short_name || '')
//             ].join(' ');
//         }
//     });
// }
//
// function searchAddress() {
//
//     var addressInput = document.getElementById('address-input').value;
//     var geocoder = new google.maps.Geocoder();
//     geocoder.geocode({address: addressInput}, function (results, status) {
//         if (status == google.maps.GeocoderStatus.OK) {
//             map.setCenter(results[0].geometry.location);
//             createMarker(myResult); // call the function that adds the marker
//             map.setCenter(myResult);
//             map.setZoom(12);
//         } else { // if status value is not equal to "google.maps.GeocoderStatus.OK"
//
//             // warning message
//             alert("The Geocode was not successful for the following reason: " + status);
//
//         }
//     });
//
//
// }
//
// $("#address-input").autocomplete({
//     //Определяем значение для адреса при геокодировании
//     source: function (request, response) {
//         geocoder.geocode({'address-input': request.term}, function (results, status) {
//             response($.map(results, function (item) {
//                 return {
//                     label: item.formatted_address,
//                     value: item.formatted_address,
//                     latitude: item.geometry.location.lat(),
//                     longitude: item.geometry.location.lng()
//                 }
//             }));
//         })
//     },
//     //Выполняется при выборе конкретного адреса
//     select: function (event, ui) {
//         //$("#latitude").val(ui.item.latitude);
//         //$("#longitude").val(ui.item.longitude);
//         var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
//         usermarker.setPosition(location);
//         map.setCenter(location);
//     }
// }); //Добавляем слушателя события обратного геокодирования для маркера при его перемещении
//
// function createMarker(latlng) {
//
//     // If the user makes another search you must clear the marker variable
//     if (marker != undefined && marker != '') {
//         marker.setMap(null);
//         marker = '';
//     }
//
//     marker = new google.maps.Marker({
//         map: map,
//         position: latlng
//     });
//
// }
//
// $(document).ready(function () {
//     initialize();
//
//
// });
// // $(document).ready(function () {
// //     var map;
// //
// //     function initMap() {
// //         map = new google.maps.Map(document.getElementById('map'), {
// //             center: {lat: -34.397, lng: 150.644},
// //             zoom: 8
// //         });
// //     }
// // });
