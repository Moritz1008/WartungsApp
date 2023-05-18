package com.example.wartungsapp;

public class Vehicle {
    private String name;
    private int mileage;
    private String imageURL;

    public Vehicle(String name, int mileage, String imageURL) {
        this.name = name;
        this.mileage = mileage;
        this.imageURL = imageURL;
    }


    // just created these, might delete some later if not needed
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", mileage=" + mileage +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
