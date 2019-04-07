package com.springboot.repository;

import com.springboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("routeRepository")
public interface RouteRepository extends JpaRepository<Route, Integer>{

    Route findByStartlocation(String startlocation);

    @Query(value = "SELECT u FROM Route u WHERE u.active = 1")
    List<Route> findAllActiveRoutes();

    @Transactional
    @Modifying
    @Query("delete from Route e where e.routeid=:x")
    public void deleteRoute(@Param("x") long routeid);

}
