package com.springboot.repository;

import com.springboot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByVehicleId(int vehicleId);


}
