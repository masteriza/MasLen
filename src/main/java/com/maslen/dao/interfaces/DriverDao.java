package com.maslen.dao.interfaces;

import com.maslen.models.Route;

public interface DriverDao {
    Route addRoute(Route route);

    Route getRoute(int userId);
}
