package com.vanpool.vanpool.Entity;

public class Driver {
    private int id;
    private String username;
    private String password;

    public Driver(int id, String name, String password) {
        this.id = id;
        this.username = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}