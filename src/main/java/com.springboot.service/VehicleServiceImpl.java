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

    public List<Vehicle> findVehicle(List<String> dayList){

        List<Vehicle> vehicleList = vehicleRepository.findAll();
        for (String day : dayList) {
            for (Vehicle vehicle: vehicleList) {

                switch (day) {
                    case "Sunday":
                        if (vehicle.isSunday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Monday":
                        if (vehicle.isMonday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Tuesday":
                        if (vehicle.isTuesday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Wednesday":
                        if (vehicle.isWednesday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Thursday":
                        if (vehicle.isThursday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Friday":
                        if (vehicle.isFriday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Saturday":
                        if (vehicle.isSaturday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    default:
                        // code block
                }
            }
        }
        return vehicleList;

    }

}