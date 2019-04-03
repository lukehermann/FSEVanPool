package com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="route")
public class Route {

    @Id
    @Column(name="driver_id")
    private int driver_id;

    @Column(name="start_location")
    private String start_location;

    @Column(name="drop_off_location")
    private String drop_off_location;

    @Column(name="active")
    private int active;

    @Column(name="rate")
    private int rate;

    @Column(name="number_of_passengers")
    private int number_of_passengers;

    @Column(name="passenger_capacity")
    private int passenger_capacity;

    public int getDriverId() {
            return driver_id;
        }
    public void setDriverId(int id) {
            this.driver_id = id;
        }

    public String getStartLocation() {
            return start_location;
        }

    public void setStartLocation(String startLocation)
    {
        this.start_location = startLocation;
    }
    public String getDropOffLocation() {
        return drop_off_location;
    }

    public void setDropOffLocation(String dropOffLocation)
    {
        this.drop_off_location = dropOffLocation;
    }

    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getActiveStatus() {
        return active;
    }
    public void setActiveStatus(int active) {
        this.active = active;
    }

    public int getNumberOfPassengers() {
        return number_of_passengers;
    }
    public void addPassengers() {
        this.number_of_passengers++;
    }
    public void subtractPassengers() {
        if (this.number_of_passengers>0){this.number_of_passengers--;}
    }

    public int getPassengerCapacity() {
        return passenger_capacity;
    }
    public void setPassengerCapacity(int passengerCapacity) {
        this.passenger_capacity = passengerCapacity;
    }


}
