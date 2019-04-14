package com.springboot.service;

import com.springboot.model.Route;

import java.util.List;

public interface RouteService {
    public void findRouteByDropOffLocation();
    public void saveRoute(Route route);
    public List<Route> listAll();
    public void deleteRoute(long routeid);
    public void updateRouteVehicle(long routeid, int vehicleid);
    public void upadteCapacity(long routeid, int capacity);
    public void signUpRiderRoute(int passangerCapacity, long routeid);

    public Route findRouteByRouteid(int routeid);
}
