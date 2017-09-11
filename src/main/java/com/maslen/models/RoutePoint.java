package com.maslen.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes_points")
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "routePoints", referencedColumnName = "route_id")
    @JoinColumn(name = "route_id")
    private Route route;
//    private int index;
//    private double latitude;
//    private double longitude;



}
