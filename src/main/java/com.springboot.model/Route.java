package com.springboot.model;

import javax.persistence.*;

@Entity
@Table(name="route")
public class Route {

    @Id
    @Column(name="driverid")
    private int driverid;

    @Column(name="startlocation")
    private String startlocation;

    @Column(name="dropofflocation")
    private String dropofflocation;

    @Column(name="active")
    private int active;

    @Column(name="rate")
    private int rate;

    @Column(name="numberofpassengers")
    private int numberofpassengers;

    @Column(name="passengercapacity")
    private int passengercapacity;

    public int getDriverId() {
            return driverid;
        }
    public void setDriverId(int id) {
            this.driverid = id;
        }

    public String getStartLocation() {
            return startlocation;
        }

    public void setStartLocation(String startLocation)
    {
        this.startlocation = startLocation;
    }
    public String getDropOffLocation() {
        return dropofflocation;
    }

    public void setDropOffLocation(String dropOffLocation)
    {
        this.dropofflocation = dropOffLocation;
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
        return numberofpassengers;
    }
    public void addPassengers() {
        this.numberofpassengers++;
    }
    public void subtractPassengers() {
        if (this.numberofpassengers>0){this.numberofpassengers--;}
    }

    public int getPassengerCapacity() {
        return passengercapacity;
    }
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengercapacity = passengerCapacity;
    }


}
