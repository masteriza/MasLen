package com.maslen.dao.interfaces;

import com.maslen.models.Route;

import java.util.List;

public interface PassengerDao {
    Route addRoute(Route route);

    Route getRoute(int userId);

    List<Route> getAllRoute();

    int deleteRoute(int routeId);
}
