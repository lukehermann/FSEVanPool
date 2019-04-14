package com.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long routeid;

    @Column(name="driverid")
    private int driverid;

    @Column(name="startlocation")
    private String startlocation;

    @Column(name="dropofflocation")
    private String dropofflocation;

    @Column(name="active")
    private int active=1;

    @Column(name="rate")
    private float rate;

    @Column(name="numberofpassengers")
    private int numberofpassengers;

    @Column(name="passengercapacity")
    private int passengercapacity;

    @Column(name = "vehicleid")
    private int vehicleid;

    public int getDriverId() {
        return driverid;
    }
    public void setDriverId(int id) {
        this.driverid = id;
    }

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public long getRouteid() {
        return routeid;
    }

    public void setRouteid(long routeid) {
        this.routeid = routeid;
    }

    public void addPassengers() {
        this.numberofpassengers++;
    }
    public void subtractPassengers() {
        if (this.numberofpassengers>0){this.numberofpassengers--;}
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}