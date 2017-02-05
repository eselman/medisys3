package com.eselman.medisys.entities;

import java.io.Serializable;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */
public class Address implements Serializable{
    private Long id;
    private String street;
    private String number;
    private String floor;
    private String apartment;

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

