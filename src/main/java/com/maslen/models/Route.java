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
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int routeId;

    private int userId;

    private double startRouteLatitude;
    private double startRouteLongitude;

    private double finishRouteLatitude;
    private double finishRouteLongitude;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "FK_routes_points_route_id"), referencedColumnName = "route_id")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<RoutePoint> routePoints = new ArrayList<>();
}
