function initialize() {   //Определение карты

    var latlng = new google.maps.LatLng(50.4501, 30.5234);
    var options = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("map_canvas"), options);

    geocoder = new google.maps.Geocoder();//Определение геокодера
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
}


$(document).ready(function () {

    initialize();

    google.maps.event.addListener(passengerStartMarker, 'dragend', function () {
        geocoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('#address-input').val(results[0].formatted_address);
                }
            }
        });
    });

    google.maps.event.addListener(map, 'click', function (event) {

        passengerStartMarker.setPosition(event.latLng);
        geocoder.geocode({'latLng': passengerStartMarker.getPosition()}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('#address-input').val(results[0].formatted_address);
                }
            }
        });
    });


    $("#address-input").autocomplete({
        //Определяем значение для адреса при геокодировании
        source: function (request, response) {
            geocoder.geocode({'address': request.term}, function (results, status) {
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


});