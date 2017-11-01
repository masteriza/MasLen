package com.maslen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Route {
    //    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int routeId;

    //    @JsonView(Views.Public.class)
    private int userId;

    //    @JsonView(Views.Public.class)
    private double startRouteLatitude;
    //    @JsonView(Views.Public.class)
    private double startRouteLongitude;

    //    @JsonView(Views.Public.class)
    private double finishRouteLatitude;
    //    @JsonView(Views.Public.class)
    private double finishRouteLongitude;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "FK_routes_points_route_id"), referencedColumnName = "route_id")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
//    @JsonView(Views.Public.class)
    @JsonIgnore
    private List<RoutePoint> routePoints = new ArrayList<>();
}
