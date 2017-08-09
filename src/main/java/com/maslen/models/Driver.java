package com.maslen.models;

import java.util.ArrayList;

public class Driver {
    private int driverId;
    private int userId;

    private double startRouteLatitude;
    private double startRouteLongitude;

    private double finishRouteLatitude;
    private double finishRouteLongitude;

    private ArrayList<RoutePoint> routePoints;

    public Driver() {
    }

    public Driver(int driverId, int userId, double startRouteLatitude, double startRouteLongitude, double finishRouteLatitude, double finishRouteLongitude) {
        this.driverId = driverId;
        this.userId = userId;
        this.startRouteLatitude = startRouteLatitude;
        this.startRouteLongitude = startRouteLongitude;
        this.finishRouteLatitude = finishRouteLatitude;
        this.finishRouteLongitude = finishRouteLongitude;
    }

    public Driver(int driverId, int userId, double startRouteLatitude, double startRouteLongitude, double finishRouteLatitude, double finishRouteLongitude, String legs) {
        this.driverId = driverId;
        this.userId = userId;
        this.startRouteLatitude = startRouteLatitude;
        this.startRouteLongitude = startRouteLongitude;
        this.finishRouteLatitude = finishRouteLatitude;
        this.finishRouteLongitude = finishRouteLongitude;
        this.routePoints = routePoints;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getStartRouteLatitude() {
        return startRouteLatitude;
    }

    public void setStartRouteLatitude(double startRouteLatitude) {
        this.startRouteLatitude = startRouteLatitude;
    }

    public double getStartRouteLongitude() {
        return startRouteLongitude;
    }

    public void setStartRouteLongitude(double startRouteLongitude) {
        this.startRouteLongitude = startRouteLongitude;
    }

    public double getFinishRouteLatitude() {
        return finishRouteLatitude;
    }

    public void setFinishRouteLatitude(double finishRouteLatitude) {
        this.finishRouteLatitude = finishRouteLatitude;
    }

    public double getFinishRouteLongitude() {
        return finishRouteLongitude;
    }

    public void setFinishRouteLongitude(double finishRouteLongitude) {
        this.finishRouteLongitude = finishRouteLongitude;
    }

    public ArrayList getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(ArrayList routePoints) {
        this.routePoints = routePoints;
    }
}