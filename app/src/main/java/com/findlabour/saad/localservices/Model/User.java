package com.findlabour.saad.localservices.Model;

/**
 * Created by Samsung on 02-Mar-18.
 */

public class User {
    private String phoneNumber;
    private String name;

    public User(){}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String phoneNumber, String name) {

        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
