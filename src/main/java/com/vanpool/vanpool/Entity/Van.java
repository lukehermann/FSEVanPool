package com.vanpool.vanpool.Entity;

public class Van {

    // ID, Location

    private int id;
    private String location;

    public Van(int id, String location) {

        this.id = id;
        this.location = location;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
