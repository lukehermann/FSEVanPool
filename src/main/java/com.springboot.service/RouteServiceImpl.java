package com.springboot.service;

import com.springboot.model.Route;
import com.springboot.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
