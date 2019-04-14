package com.springboot.repository;

import com.springboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository("routeRepository")
public interface RouteRepository extends JpaRepository<Route, Integer>{

    Route findByStartlocation(String startlocation);

    @Transactional
    @Modifying
    @Query("delete from Route e where e.routeid=:x")
    public void deleteRoute(@Param("x") long routeid);

    @Modifying
    @Query("update Route u set u.vehicleid = :vehicleid where u.routeid = :routeid")
    void updateRouteVehicle(long routeid, int vehicleid);

    @Modifying
    @Query("update Route u set u.passengercapacity = :capacity where u.routeid = :routeid")
    void updateCapacity(long routeid, int capacity);

    @Query("update Route u set u.numberofpassengers = u.numberofpassengers + 1 where u.routeid = :routeid")
    void signUpRiderRoute(long routeid);


}
