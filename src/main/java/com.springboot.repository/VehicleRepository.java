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
    @Query("update Vehicle u set u.sunday = true where u.vehicleId = :vehicleid")
    void updateSunday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.monday = true where u.vehicleId = :vehicleid")
    void updateMonday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.tuesday = true where u.vehicleId = :vehicleid")
    void updateTuesday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.wednesday = true where u.vehicleId = :vehicleid")
    void updateWednesday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.thursday = true where u.vehicleId = :vehicleid")
    void updateThursday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.friday = true where u.vehicleId = :vehicleid")
    void updateFriday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.saturday = true where u.vehicleId = :vehicleid")
    void updateSaturday(int vehicleid);

    @Transactional
    @Modifying
    @Query("delete from Vehicle e where e.vehicleId=:x")
    void deleteVehicle(@Param("x") int routeid);
}
