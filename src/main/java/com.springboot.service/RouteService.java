package com.springboot.service;

import com.springboot.model.Route;

import java.util.List;

public interface RouteService {
    void findRouteByDropOffLocation();
    void saveRoute(Route route);
    List<Route> listAll();
    void deleteRoute(long routeid);
    void updateRouteVehicle(long routeid, int vehicleid);
    void upadteCapacity(long routeid, int capacity);
    //void signUpRiderRoute(int passangerCapacity, long routeid);
    void signUpRiderRoute(int numberofpassengers , long routeid);

    void addDriverToRoute(int driverid, long routeid);

    Route findRouteByRouteid(int routeid);
}
