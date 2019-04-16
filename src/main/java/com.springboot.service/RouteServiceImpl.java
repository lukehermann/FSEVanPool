package com.springboot.service;

import com.springboot.model.Route;
import com.springboot.model.Vehicle;
import com.springboot.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;



    @Override
    public void findRouteByDropOffLocation() {

    }

    @Override
    public void saveRoute(Route route)
    {
        routeRepository.save(route);
    }

    public List<Route> listAll() {

        //return routeRepository.findAllRoutes();
        return routeRepository.findAll();
    }

    @Override
    public void deleteRoute(long routeid) {
        routeRepository.deleteRoute(routeid);
    }

    @Override
    public void signUpRiderRoute(int passengerCapacity, long routeid) {
        routeRepository.signUpRiderRoute(passengerCapacity,routeid);
    }

    @Override
    @Transactional
    public void updateRouteVehicle(long routeid, int vehicleid) {
        routeRepository.updateRouteVehicle(routeid, vehicleid);
    }

    @Override
    @Transactional
    public void upadteCapacity(long routeid, int capacity) {
        routeRepository.updateCapacity(routeid, capacity);
    }
    public Route findRouteByRouteid(int routeid)
    {
        return routeRepository.findRouteByID(routeid);
    }

    @Override
    public void addDriverToRoute(int driverid, long routeid)
    {
        routeRepository.updateRouteDriver(routeid, driverid);
    }

    @Override
    public List<Route> listActive() {
        return routeRepository.findAllActiveRoutes();
    }

    @Override
    public List<Route> listNoDriverID() {
        return routeRepository.findAllWithoutDriverID();
    }

    @Override
    public void setRouteToActive(long routeid)
    {
        routeRepository.setRouteToActive(routeid);
    }

    @Override
    public int getVehicleID(long routeid) {
        return routeRepository.getVehicleId(routeid);
    }

    @Override
    public List<String> getDays(long routeid) {
        List<String> dayList = new ArrayList<>();

        Route route = routeRepository.findRouteByID(routeid);

        if(route.isSunday()){
            dayList.add("Sunday");
        }
        if(route.isMonday()){
            dayList.add("Monday");
        }
        if(route.isTuesday()){
            dayList.add("Tuesday");
        }
        if(route.isWednesday()){
            dayList.add("Wednesday");
        }
        if(route.isThursday()){
            dayList.add("Thursday");
        }
        if(route.isFriday()){
            dayList.add("Friday");
        }
        if(route.isSaturday()){
            dayList.add("Saturday");
        }

        return dayList;
    }
}
