package com.springboot.service;

import com.springboot.model.Vehicle;
import com.springboot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Vehicle> findVehicle(List<String> dayList){

        List<Vehicle> vehicleList = vehicleRepository.findAll();
        for (String day : dayList) {
            for (int vehicle=0; vehicle < vehicleList.size(); vehicle++) {
                switch (day) {
                    case "Sunday":
                        if (vehicleList.get(vehicle).isSunday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Monday":
                        if (vehicleList.get(vehicle).isMonday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Tuesday":
                        if (vehicleList.get(vehicle).isTuesday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Wednesday":
                        if (vehicleList.get(vehicle).isWednesday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Thursday":
                        if (vehicleList.get(vehicle).isThursday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Friday":
                        if (vehicleList.get(vehicle).isFriday()) {
                            vehicleList.remove(vehicle);
                        }
                        break;
                    case "Saturday":
                        if (vehicleList.get(vehicle).isSaturday()) {
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

    @Override
    @Transactional
    public void updateDays(List<String> listOfDays, int vehicleid){

        for (String day : listOfDays) {
            switch (day) {
                case "Sunday":

                    vehicleRepository.updateSunday(vehicleid);
                    break;
                case "Monday":

                    vehicleRepository.updateMonday(vehicleid);
                    break;
                case "Tuesday":

                    vehicleRepository.updateTuesday(vehicleid);
                    break;
                case "Wednesday":

                    vehicleRepository.updateWednesday(vehicleid);
                    break;
                case "Thursday":

                    vehicleRepository.updateThursday(vehicleid);
                    break;
                case "Friday":

                    vehicleRepository.updateFriday(vehicleid);
                    break;
                case "Saturday":

                    vehicleRepository.updateSaturday(vehicleid);
                    break;
                default:
                    // code block
                    break;
            }
        }
    }

    @Override
    public Vehicle getVehicle(int vehicleid) {
        return vehicleRepository.findByVehicleId(vehicleid);
    }
}