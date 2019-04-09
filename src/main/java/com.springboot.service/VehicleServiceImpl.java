package com.springboot.service;

import com.springboot.model.Vehicle;
import com.springboot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void saveVehicle(Vehicle vehicle) {

        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> listAll() {
        return vehicleRepository.findAll();
    }
}