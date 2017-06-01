package com.maslen.beans;

public class RoutePoint {
    private int index;
    private double latitude;
    private double longitude;

    public RoutePoint() {
    }

    public RoutePoint(int index, double latitude, double longitude) {
        this.index = index;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
