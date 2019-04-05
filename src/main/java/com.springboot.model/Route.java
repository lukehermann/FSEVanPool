package com.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @Column(name="driverid")
    private int driverid;


    @Column(name="startlocation")
    private String startlocation;

    @Column(name="dropofflocation")
    private String dropofflocation;

    @Column(name="active")
    private int active=1;

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


    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getDropofflocation() {
        return dropofflocation;
    }

    public void setDropofflocation(String dropofflocation) {
        this.dropofflocation = dropofflocation;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
