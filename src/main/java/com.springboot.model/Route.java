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
    private int active=0;

    @Column(name="rate")
    private float rate;

    @Column(name="numberofpassengers")
    private int numberofpassengers;

    @Column(name="passengercapacity")
    private int passengercapacity;

    @Column(name = "vehicleid")
    private int vehicleid;

    @Column(name = "sunday")
    private boolean sunday=false;

    @Column(name = "monday")
    private boolean monday=false;

    @Column(name = "tuesday")
    private boolean tuesday=false;

    @Column(name = "wednesday")
    private boolean wednesday=false;

    @Column(name = "thursday")
    private boolean thursday=false;

    @Column(name = "friday")
    private boolean friday=false;

    @Column(name = "saturday")
    private boolean saturday=false;


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

    public int getNumberofpassengers() {
        return numberofpassengers;
    }

    public void setNumberofpassengers(int numberofpassengers) {
        this.numberofpassengers = numberofpassengers;
    }

    public int getPassengercapacity() {
        return passengercapacity;
    }

    public void setPassengercapacity(int passengercapacity) {
        this.passengercapacity = passengercapacity;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }
}