package com.maslen.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    private int routeId;

    private int userId;

    private double startRouteLatitude;
    private double startRouteLongitude;

    private double finishRouteLatitude;
    private double finishRouteLongitude;

    //@OneToMany
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "FK_routes_points_route_id"))
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<RoutePoint> routePoints = new ArrayList<>();
}
