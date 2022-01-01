package com.project.scheduler.entity;

public enum LatitudeDirection {
    N("S"),
    S("J");

    private final String displayValue;

    private LatitudeDirection(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
