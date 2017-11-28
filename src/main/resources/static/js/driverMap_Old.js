function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {
            lat: 28.6247,
            lng: 77.3731
        },
        disableDefaultUI: true,
    });

    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        suppressMarkers: true
    });

    var response = {
        "abcd": {
            "points": [
                [28.5435, 77.2051, "2016-07-17 08:02:18 - 28.543500 - 77.205100"],
                [28.5313, 77.249, "2016-07-17 12:32:38 - 28.531300 - 77.249000"],
                [28.5279, 77.2462, "2016-07-17 12:35:02 - 28.527900 - 77.246200"],
                [28.5328, 77.2391, "2016-07-17 12:36:42 - 28.532800 - 77.239100"],
                [28.5407, 77.24, "2016-07-17 12:38:23 - 28.540700 - 77.240000"],
                [28.543, 77.2139, "2016-07-17 12:42:36 - 28.543000 - 77.213900"],
                [28.5429, 77.2095, "2016-07-17 12:43:26 - 28.542900 - 77.209500"],
                [28.5332, 77.2071, "2016-07-17 12:48:30 - 28.533200 - 77.207100"],
                [28.531, 77.2113, "2016-07-17 12:52:43 - 28.531000 - 77.211300"],
                [28.5315, 77.2077, "2016-07-17 13:03:15 - 28.531500 - 77.207700"],
                [28.5433, 77.2117, "2016-07-17 13:08:27 - 28.543300 - 77.211700"],
                [28.5412, 77.2374, "2016-07-17 13:12:50 - 28.541200 - 77.237400"],
                [28.5311, 77.2482, "2016-07-17 17:49:02 - 28.531100 - 77.248200"]
            ]
        },
        "ret-abcd": {
            "points": [
                [28.5294, 77.2502, "2016-07-18 07:29:38 - 28.529400 - 77.250200"],
                [28.5541, 77.2637, "2016-07-18 07:43:59 - 28.554100 - 77.263700"],
                [28.5635, 77.2648, "2016-07-18 07:46:08 - 28.563500 - 77.264800"],
                [28.5721, 77.2579, "2016-07-18 07:48:07 - 28.572100 - 77.257900"],
                [28.5746, 77.2608, "2016-07-18 07:49:14 - 28.574600 - 77.260800"],
                [28.5787, 77.2813, "2016-07-18 07:51:16 - 28.578700 - 77.281300"],
                [28.5731, 77.3086, "2016-07-18 07:52:39 - 28.573100 - 77.308600"],
                [28.5902, 77.336, "2016-07-18 07:58:00 - 28.590200 - 77.336000"],
                [28.6252, 77.3735, "2016-07-18 08:49:47 - 28.625200 - 77.373500"]
            ]
        }
    };

    var cnt = 0;
    var markers = [];
    var linecolors = ['red', 'blue', 'green', 'yellow'];
    var colorIdx = 0;
    var dd = [];

    for (key in response) {
        if (response[key].points.length > 0) {
            var blocks = [];
            var k = 0;
            for (var i = 0; i < response[key].points.length; i++) {
                if (i != 0 && i % 10 == 0) {
                    k++;
                }
                //console.log(k);
                if (typeof blocks[k] == 'undefined') {
                    blocks[k] = [];
                }

                blocks[k].push(response[key].points[i]);
            }

            ds = new google.maps.DirectionsService;

            for (i = 0; i < blocks.length; i++) {
                waypts = [];
                markers.push([blocks[i][0][0], blocks[i][0][1], blocks[i][0][2]]);
                for (var j = 1; j < blocks[i].length - 1; j++) {
                    waypts.push({
                        location: blocks[i][j][0] + ',' + blocks[i][j][1],
                        stopover: true
                    });
                    markers.push([blocks[i][j][0], blocks[i][j][1], blocks[i][j][2]]);
                }
                markers.push([blocks[i][blocks[i].length - 1][0], blocks[i][blocks[i].length - 1][1], blocks[i][blocks[i].length - 1][2]]);

                ds.route({
                        'origin': blocks[i][0][0] + ',' + blocks[i][0][1],
                        'destination': blocks[i][blocks[i].length - 1][0] + ',' + blocks[i][blocks[i].length - 1][1],
                        'waypoints': waypts,
                        'travelMode': 'DRIVING'
                    },
                    function (directions, status) {
                        //console.log(dd[m]);
                        dd.push(new google.maps.DirectionsRenderer({
                            suppressInfoWindows: true,
                            suppressMarkers: true,
                            polylineOptions: {
                                strokeColor: linecolors[colorIdx++ % 3]
                            },
                            map: map
                        }));

                        if (status == google.maps.DirectionsStatus.OK) {
                            dd[dd.length - 1].setDirections(directions);
                        }
                    }
                );

            }
        }

        for (h = 0; h < markers.length; h++) {
            createMapMarker(map, new google.maps.LatLng(markers[h][0], markers[h][1]), markers[h][2], "", "");
        }
        cnt++;

    }
}

function createMapMarker(map, latlng, label, html, sign) {
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        icon: "http://www.google.com/mapfiles/marker" + sign + ".png",
        title: label,
        //zIndex: Math.round(latlng.lat()*-100000)<<5
    });

    marker.myname = label;

    return marker;
}

google.maps.event.addDomListener(window, "load", initMap);

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////

// Initialise some variables
var directionsService = new google.maps.DirectionsService();
var num, map, data;
var requestArray = [], renderArray = [];

// A JSON Array containing some people/routes and the destinations/stops
var jsonArray = {
    "Person 1": ["Torquay", "Teignmouth", "Dawlish", "Exeter"],
    "Person 2": ["Exmouth", "Sidmouth", "Taunton", "Crediton", "Okehampton"],
    "Person 3": ["Penzance", "Truro", "Bodmin", "Falmouth"]
}

// 16 Standard Colours for navigation polylines
var colourArray = ['navy', 'grey', 'fuchsia', 'black', 'white', 'lime', 'maroon', 'purple', 'aqua', 'red', 'green', 'silver', 'olive', 'blue', 'yellow', 'teal'];

// Let's make an array of requests which will become individual polylines on the map.
function generateRequests() {

    requestArray = [];

    for (var route in jsonArray) {
        // This now deals with one of the people / routes

        // Somewhere to store the wayoints
        var waypts = [];

        // 'start' and 'finish' will be the routes origin and destination
        var start, finish

        // lastpoint is used to ensure that duplicate waypoints are stripped
        var lastpoint

        data = jsonArray[route]

        limit = data.length
        for (var waypoint = 0; waypoint < limit; waypoint++) {
            if (data[waypoint] === lastpoint) {
                // Duplicate of of the last waypoint - don't bother
                continue;
            }

            // Prepare the lastpoint for the next loop
            lastpoint = data[waypoint]

            // Add this to waypoint to the array for making the request
            waypts.push({
                location: data[waypoint],
                stopover: true
            });
        }

        // Grab the first waypoint for the 'start' location
        start = (waypts.shift()).location;
        // Grab the last waypoint for use as a 'finish' location
        finish = waypts.pop();
        if (finish === undefined) {
            // Unless there was no finish location for some reason?
            finish = start;
        } else {
            finish = finish.location;
        }

        // Let's create the Google Maps request object
        var request = {
            origin: start,
            destination: finish,
            waypoints: waypts,
            travelMode: google.maps.TravelMode.DRIVING
        };

        // and save it in our requestArray
        requestArray.push({"route": route, "request": request});
    }

    processRequests();
}

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
                preserveViewport: true,
                suppressInfoWindows: true,
                polylineOptions: {
                    strokeWeight: 4,
                    strokeOpacity: 0.8,
                    strokeColor: colourArray[i]
                },
                markerOptions: {
                    icon: {
                        path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
                        scale: 3,
                        strokeColor: colourArray[i]
                    }
                }
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

// Called Onload
function init() {

    // Some basic map setup (from the API docs)
    var mapOptions = {
        center: new google.maps.LatLng(50.677965, -3.768841),
        zoom: 8,
        mapTypeControl: false,
        streetViewControl: false,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    // Start the request making
    generateRequests()
}

// Get the ball rolling and trigger our init() on 'load'
google.maps.event.addDomListener(window, 'load', init);


//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

var geocoder;
var map;

function initialize() {
    var map = new google.maps.Map(
        document.getElementById("map_canvas"), {
            center: new google.maps.LatLng(37.4419, -122.1419),
            zoom: 13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });
    markerStartEnd(map, new google.maps.LatLng(40.7127837, -74.00594130000002), new google.maps.LatLng(40.735657, -74.1723667), "10:00", "10:20");
    var bounds = new google.maps.LatLngBounds();
    bounds.extend(new google.maps.LatLng(40.7127837, -74.00594130000002));
    bounds.extend(new google.maps.LatLng(40.735657, -74.1723667));
    map.fitBounds(bounds);
}

google.maps.event.addDomListener(window, "load", initialize);

function markerStartEnd(map, startPoint, endPoint, startTime, endTime) {
    var anchor = new google.maps.Point(20, 41),
        size = new google.maps.Size(41, 41),
        origin = new google.maps.Point(0, 0),
        icon = new google.maps.MarkerImage('http://maps.google.com/mapfiles/ms/micons/blue.png', size, origin, anchor);
    new google.maps.Marker({
        icon: icon,
        map: map,
        position: startPoint
    });

    icon = new google.maps.MarkerImage('http://maps.google.com/mapfiles/ms/micons/green.png', size, origin, anchor);
    new google.maps.Marker({
        icon: icon,
        map: map,
        position: endPoint
    });
}
