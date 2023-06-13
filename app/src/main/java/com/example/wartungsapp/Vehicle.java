package com.example.wartungsapp;

import android.net.Uri;
import android.widget.Toast;

public class Vehicle {

    private MainActivity mainActivity;
    private String name;
    private int mileage;
    private int monthlyMileage;
    private Uri imageURI;

    public Vehicle(String name, int mileage, int monthlyMileage,  Uri imageURI) {
        this.mainActivity = new MainActivity();
        this.name = name;
        this.mileage = mileage;
        this.monthlyMileage = monthlyMileage;
        this.imageURI = imageURI;
    }

    public void save() {
        //TODO: save current instance
        Toast.makeText(mainActivity, this.toString(), Toast.LENGTH_SHORT).show();
        mainActivity.addVehicle(this);
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

    public Uri getImageURI() {
        return imageURI;
    }

    public void setImageURI(Uri imageURL) {
        this.imageURI = imageURI;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", mileage=" + mileage +
                ", monthlyMileage=" + monthlyMileage +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }
}
