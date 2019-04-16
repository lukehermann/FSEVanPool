package com.springboot.repository;

import com.springboot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "SELECT u FROM Vehicle u WHERE u.vehicleId = :vehicleid")
    Vehicle findByVehicleId(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.sunday = :change where u.vehicleId = :vehicleid")
    void updateSunday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.monday = :change where u.vehicleId = :vehicleid")
    void updateMonday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.tuesday = :change where u.vehicleId = :vehicleid")
    void updateTuesday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.wednesday = :change where u.vehicleId = :vehicleid")
    void updateWednesday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.thursday = :change where u.vehicleId = :vehicleid")
    void updateThursday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.friday = :change where u.vehicleId = :vehicleid")
    void updateFriday(int vehicleid, boolean change);

    @Modifying
    @Query("update Vehicle u set u.saturday = :change where u.vehicleId = :vehicleid")
    void updateSaturday(int vehicleid, boolean change);

    @Transactional
    @Modifying
    @Query("delete from Vehicle e where e.vehicleId=:x")
    void deleteVehicle(@Param("x") int routeid);
}
