package com.springboot.service;

import com.springboot.model.Vehicle;
import java.util.List;

public interface VehicleService {

    void saveVehicle(Vehicle vehicle);
    List<Vehicle> listAll();
    List<Vehicle> findVehicle(List<String> dayList);
    void updateDays(List<String> listOfDays, int vehicleid, boolean change);
    Vehicle getVehicle(int vehicleid);
    void deleteVehicle(int vehicleid);
}