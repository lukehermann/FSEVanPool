package com.springboot.repository;

import com.springboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("routeRepository")
public interface RouteRepository extends JpaRepository<Route, Integer>{

    Route findByStartlocation(String startlocation);

    @Query(value = "SELECT u FROM Route u WHERE u.active = 1")
    List<Route> findAllRoutes();

}
