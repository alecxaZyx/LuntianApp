package com.example.luntian.model;

public class GrowthModel {
    float day, height;

    public GrowthModel() {
    }

    public GrowthModel(float day, float height) {
        this.day = day;
        this.height = height;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
