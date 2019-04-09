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
    private int rate;

    @Column(name="numberofpassengers")
    private int numberofpassengers;

    @Column(name="passengercapacity")
    private int passengercapacity;


    public long getRouteid() {
        return routeid;
    }

    public void setRouteid(long routeid) {
        this.routeid = routeid;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
}