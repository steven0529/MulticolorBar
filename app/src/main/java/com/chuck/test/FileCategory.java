package com.chuck.test;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class FileCategory {

    private String name;
    private double fileSize;
    private String unit;
    private String color;

    public FileCategory(String name, double fileSize, String unit, String color) {
        this.name = name;
        this.fileSize = fileSize;
        this.unit = unit;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
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
