package com.example.wartungsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Vehicle> vehicles;
    private RecyclerView vehiclesRecyclerView;
    private FloatingActionButton fab;
    private Context context = this;
    private VehicleRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehiclesRecyclerView = findViewById(R.id.vehicles);
        fab = findViewById(R.id.fabAddVehicle);

        vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("Kawasaki ER-5", 62000, 2000, null ));
        vehicles.add(new Vehicle("Opel Corsa D CRE", 16000, 2000, null));
        vehicles.add(new Vehicle("Nissan 350Z", 80000, 2000, null));


        //VehicleRecViewAdapter adapter = new VehicleRecViewAdapter(this);
        adapter = new VehicleRecViewAdapter(this);
        adapter.setVehicles(vehicles);

        vehiclesRecyclerView.setAdapter(adapter);
        vehiclesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(context, CreateVehicleActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        adapter.setVehicles(vehicles);
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

    public ArrayList<Vehicle> getVehicleList() {
        return vehicles;
    }

    public void addVehicle(Vehicle newVehicle) {
        vehicles.add(newVehicle);
    }
}