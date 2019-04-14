package com.springboot.service;

import com.springboot.model.Vehicle;
import java.util.List;

public interface VehicleService {

    public void saveVehicle(Vehicle vehicle);
    public List<Vehicle> listAll();
    public List<Vehicle> findVehicle(List<String> dayList);
    public void updateDays(List<String> listOfDays, int vehicleid);
    public Vehicle getVehicle(int vehicleid);


}