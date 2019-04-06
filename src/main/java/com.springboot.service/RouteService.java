package com.springboot.service;

import com.springboot.model.Route;

import java.util.List;

public interface RouteService {
    public void findRouteByDropOffLocation();
    public void saveRoute(Route route);
    public List<Route> listAll();
    public void deleteRoute(long routeid);
}
