package com.project.scheduler.entity;

public enum LongitudeDirection {
    E("V"),
    W("Z");

    private final String displayValue;

    private LongitudeDirection(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
