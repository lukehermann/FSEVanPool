package com.springboot.repository;

import com.springboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("routeRepository")
public interface RouteRepository extends JpaRepository<Route, Long>{

    Route findByStartlocation(String startlocation);

}