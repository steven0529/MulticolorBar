package com.chuck.test;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class Product {

    private String name;
    private int salesVolume;
    private String unit;
    private String color;

    public Product(String name, int salesVolume, String unit, String color) {
        this.name = name;
        this.salesVolume = salesVolume;
        this.unit = unit;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
