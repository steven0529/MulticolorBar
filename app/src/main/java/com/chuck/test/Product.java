package com.chuck.test;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class Product {

    private String name;
    private int salesVolume;

    public Product(String name, int salesVolume) {
        this.name = name;
        this.salesVolume = salesVolume;
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
}
