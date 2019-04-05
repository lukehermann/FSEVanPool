package com.springboot.service;

import com.springboot.model.Route;

public interface RouteService {
    public void findRouteByDropOffLocation();
    public void saveRoute(Route route);
}
