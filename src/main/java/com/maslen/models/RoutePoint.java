package com.maslen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes_points")
@ToString(exclude = "route")
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    @JsonView(Views.Public.class)
    private int pointId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
//    @JsonView(Views.Public.class)
    private Route route;

    @Column(name = "index_point", nullable = false)
//    @JsonView(Views.Public.class)
    private int indexPoint;

    @Column(nullable = false)
//    @JsonView(Views.Public.class)
    private double latitude;

    @Column(nullable = false)
//    @JsonView(Views.Public.class)
    private double longitude;
}
