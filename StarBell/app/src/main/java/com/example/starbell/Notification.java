package com.example.starbell;

import java.util.ArrayList;

public class Notification {

    private String name;
    private String description;
    private int hour;
    private int minutes;
    private boolean vibration;
    private String days;
    private String email;

    public Notification(String nam, String description, int hour, int minutes, boolean vibration, String days, String email) {
        this.name = nam;
        this.description = description;
        this.hour = hour;
        this.minutes = minutes;
        this.vibration = vibration;
        this.days = days;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isVibration() {
        return vibration;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
