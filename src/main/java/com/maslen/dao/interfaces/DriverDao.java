package com.maslen.dao.interfaces;

import com.maslen.models.Route;

import java.util.List;

public interface DriverDao {
    Route addRoute(Route route);

    Route getRoute(int userId);

    List<Route> getAllRoute();
}
