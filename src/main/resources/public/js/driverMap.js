function initMap() {
    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), options);
    //var map = new google.maps.Map(document.getElementById('map_canvas'), {
    //     center: {lat: -33.8688, lng: 151.2195},
    //     zoom: 13
    // });
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
