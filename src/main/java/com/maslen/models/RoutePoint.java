package com.maslen.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes_points")
//@ToString(exclude = "route")
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int pointId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "route_id")

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "index_point", nullable = false)
    private int indexPoint;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
