package com.example.starbell;

public class DayItem {
    private String dayName;
    private boolean isChecked;

    public DayItem(String dayName, boolean isChecked) {
        this.dayName = dayName;
        this.isChecked = isChecked;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
