package com.vanpool.vanpool.Entity;

public class Rider extends User{
    public Rider(int id, String username, String password) {
        super(id, username, password);
    }
}
/*
public class Rider {

    private int id;
    private String username;
    private String password;
    private String pickUpLocation;
    private String dropOffLocation;


    public Rider(int id, String username, String password, String pickUpLocation, String dropOffLocation) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }
}*/
