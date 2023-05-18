package com.example.wartungsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView vehiclesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehiclesRecyclerView = findViewById(R.id.vehicles);

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("Kawasaki ER-5", 62000, "https://de.wikipedia.org/wiki/Kawasaki_ER-5#/media/Datei:Kawasaki_ER-5_2.JPG"));
        vehicles.add(new Vehicle("Opel Corsa D CRE", 160000, "https://www.autozeitung.de/assets/styles/article_image/public/field/image/opel-corsa-color-race-001.jpg"));
        vehicles.add(new Vehicle("Kawasaki ER-5", 62000, "https://de.wikipedia.org/wiki/Kawasaki_ER-5#/media/Datei:Kawasaki_ER-5_2.JPG"));
        vehicles.add(new Vehicle("Opel Corsa D CRE", 160000, "https://www.autozeitung.de/assets/styles/article_image/public/field/image/opel-corsa-color-race-001.jpg"));


        VehicleRecViewAdapter adapter = new VehicleRecViewAdapter();
        adapter.setVehicles(vehicles);

        vehiclesRecyclerView.setAdapter(adapter);
        vehiclesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.item1) {
            Toast.makeText(this, "Hello from Item 1", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.item2) {
            Toast.makeText(this, "Hello from Item 2", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}