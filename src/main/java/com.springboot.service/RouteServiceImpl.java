package com.springboot.service;

import com.springboot.model.Route;
import com.springboot.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void signUpRiderRoute(long routeid) {
        routeRepository.signUpRiderRoute(routeid);
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


}
