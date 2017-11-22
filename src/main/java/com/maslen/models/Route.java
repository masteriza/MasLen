package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
@ToString(exclude = "routePoints")
@SqlResultSetMapping(
        name = "nativeSqlResultRoute",
        entities = @EntityResult(entityClass = Route.class, fields = {
                @FieldResult(name = "routeId", column = "routeId"),
                @FieldResult(name = "userId", column = "userId"),
                @FieldResult(name = "startRouteLatitude", column = "startRouteLatitude"),
                @FieldResult(name = "startRouteLongitude", column = "startRouteLongitude"),
                @FieldResult(name = "finishRouteLatitude", column = "finishRouteLatitude"),
                @FieldResult(name = "finishRouteLongitude", column = "finishRouteLongitude")
        })
)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "routeid")
    private int routeId;

    private int userId;

    private double startRouteLatitude;
    private double startRouteLongitude;

    private double finishRouteLatitude;
    private double finishRouteLongitude;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "routeId", foreignKey = @ForeignKey(name = "FK_routes_points_route_id"), referencedColumnName = "routeId")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<RoutePoint> routePoints = new ArrayList<>();
}
