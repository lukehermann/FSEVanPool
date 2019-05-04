package com.springboot.service;

import com.springboot.model.Route;
import com.springboot.model.Vehicle;

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

    List<Route> listActive();

    List<Route> listNoDriverID();

    Route findRouteByRouteid(int routeid);

    void setRouteToActive(long routeid);

    int getVehicleID(long routeid);

    List<String> getDays(long routeid);

    void deleteRoutebyVehicleID(int vehicleid);

    void endDriverShift(long routeid);

    void setRouteInactive(long routeid);

    List<Route> getAllWithMyDriverID(int driverid);
}
