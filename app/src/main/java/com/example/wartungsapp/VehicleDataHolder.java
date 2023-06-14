package com.example.wartungsapp;

import java.util.ArrayList;

public class VehicleDataHolder {
    private static VehicleDataHolder instance;
    private ArrayList<Vehicle> vehicles;

    private VehicleDataHolder() {
        vehicles = new ArrayList<>();

        this.addVehicle(new Vehicle("Kawasaki ER-5", 62000, 2000, null ));
        this.addVehicle(new Vehicle("Opel Corsa D CRE", 16000, 2000, null));
        this.addVehicle(new Vehicle("Nissan 350Z", 80000, 2000, null));
    }


    // Yay, Singleton pattern!
    public static VehicleDataHolder getInstance() {
        if (instance == null) {
            instance = new VehicleDataHolder();
        }
        return instance;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}

