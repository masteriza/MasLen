package com.maslen.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes_points")
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "routePoints", referencedColumnName = "route_id")
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "index_point", nullable = false)
    private int indexPoint;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;


}
