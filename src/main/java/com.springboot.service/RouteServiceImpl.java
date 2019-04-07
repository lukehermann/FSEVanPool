package com.springboot.service;

import com.springboot.model.Route;
import com.springboot.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
