package com.springboot.repository;

import com.springboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class RouteRepository /*extends JpaRepository<Route, Long>*/{

    //Route findByRoute(String route);

}
