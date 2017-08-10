package com.develop.tmtai.models;

/**
 * Created by tmtai on 8/10/2017.
 */

public class Traveler {
    private String name;
    private String phone;
    private String roomNumber;
    private Traveler roommates;

    public Traveler() {
    }

    public Traveler(String name, String phone, String roomNumber, Traveler roommates) {
        this.name = name;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.roommates = roommates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Traveler getRoommates() {
        return roommates;
    }

    public void setRoommates(Traveler roommates) {
        this.roommates = roommates;
    }
}
