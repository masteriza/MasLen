SELECT
  d.route_id,
  d.userId,
  d.startRouteLatitude,
  d.startRouteLongitude,
  d.finishRouteLatitude,
  d.finishRouteLongitude,
  d.distanceBetweenStartPoints,
  d.distanceBetweenFinishPoints
FROM (SELECT
        r.route_id,
        r.userId,
        r.startRouteLatitude,
        r.startRouteLongitude,
        r.finishRouteLatitude,
        r.finishRouteLongitude,
        p.radius,
        p.distance_unit
        * DEGREES(ACOS(COS(RADIANS(p.startlatpoint))
                       * COS(RADIANS(r.startRouteLatitude))
                       * COS(RADIANS(p.startlongpoint - r.startRouteLongitude))
                       + SIN(RADIANS(p.startlatpoint))
                         * SIN(RADIANS(r.startRouteLatitude))))  AS distanceBetweenStartPoints,
        p.distance_unit
        * DEGREES(ACOS(COS(RADIANS(p.endlatpoint))
                       * COS(RADIANS(r.finishRouteLatitude))
                       * COS(RADIANS(p.endlongpoint - r.finishRouteLongitude))
                       + SIN(RADIANS(p.endlatpoint))
                         * SIN(RADIANS(r.finishRouteLatitude)))) AS distanceBetweenFinishPoints
      FROM routes AS r
        JOIN (SELECT
                50.4574433  AS startlatpoint,
                30.617241   AS startlongpoint,
                50.44715311 AS endlatpoint,
                30.53077    AS endlongpoint,
                1.0         AS radius,
                111.045     AS distance_unit) AS p
          ON 1 = 1
      WHERE r.startRouteLatitude BETWEEN p.startlatpoint - (p.radius / p.distance_unit) AND p.startlatpoint +
                                                                                            (p.radius / p.distance_unit)
            AND r.startRouteLongitude BETWEEN p.startlongpoint -
                                              (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint)))) AND
            p.startlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint))))

            AND r.finishRouteLatitude BETWEEN p.endlatpoint - (p.radius / p.distance_unit) AND p.endlatpoint +
                                                                                               (p.radius /
                                                                                                p.distance_unit)
            AND r.finishRouteLongitude BETWEEN p.endlongpoint -
                                               (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint)))) AND
            p.endlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint))))) AS d
WHERE distanceBetweenStartPoints <= radius AND d.distanceBetweenFinishPoints <= d.radius
ORDER BY distanceBetweenStartPoints
LIMIT 15