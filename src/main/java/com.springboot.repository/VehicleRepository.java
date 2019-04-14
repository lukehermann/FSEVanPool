package com.springboot.repository;

import com.springboot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "SELECT u FROM Vehicle u WHERE u.vehicleId = :vehicleid")
    Vehicle findByVehicleId(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.sunday = true where u.vehicleId = :vehicleid")
    public void updateSunday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.monday = true where u.vehicleId = :vehicleid")
    public void updateMonday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.tuesday = true where u.vehicleId = :vehicleid")
    public void updateTuesday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.wednesday = true where u.vehicleId = :vehicleid")
    public void updateWednesday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.thursday = true where u.vehicleId = :vehicleid")
    public void updateThursday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.friday = true where u.vehicleId = :vehicleid")
    public void updateFriday(int vehicleid);

    @Modifying
    @Query("update Vehicle u set u.saturday = true where u.vehicleId = :vehicleid")
    public void updateSaturday(int vehicleid);
}
