package com.maslen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routesPoints")
@ToString(exclude = "route")
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int pointId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "index_point", nullable = false)
    private int indexPoint;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
