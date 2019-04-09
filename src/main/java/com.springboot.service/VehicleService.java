package com.springboot.service;

import com.springboot.model.Vehicle;
import java.util.List;

public interface VehicleService {

    public void saveVehicle(Vehicle vehicle);
    public List<Vehicle> listAll();


}