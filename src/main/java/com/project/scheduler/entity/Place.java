package com.project.scheduler.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Place {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private String street;
    @Column(name = "street_number")
    private String number;
    private String city;
    private String zip;
    private String country;
    @OneToOne
    private GpsCoordinate location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GpsCoordinate getLocation() {
        return location;
    }

    public void setLocation(GpsCoordinate location) {
        this.location = location;
    }
}
