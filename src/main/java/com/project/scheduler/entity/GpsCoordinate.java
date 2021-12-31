package com.project.scheduler.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GpsCoordinate {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private double latDeg;
    @NotNull
    private LatitudeDirection latDir;
    @NotNull
    private double longDeg;
    @NotNull
    private LongitudeDirection longDir;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatDeg() {
        return latDeg;
    }

    public void setLatDeg(double latDeg) {
        this.latDeg = latDeg;
    }

    public LatitudeDirection getLatDir() {
        return latDir;
    }

    public void setLatDir(LatitudeDirection latDir) {
        this.latDir = latDir;
    }

    public double getLongDeg() {
        return longDeg;
    }

    public void setLongDeg(double longDeg) {
        this.longDeg = longDeg;
    }

    public LongitudeDirection getLongDir() {
        return longDir;
    }

    public void setLongDir(LongitudeDirection longDir) {
        this.longDir = longDir;
    }
}
