package com.example.wartungsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private VehicleRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehiclesRecyclerView = findViewById(R.id.vehicles);
        fab = findViewById(R.id.fabAddVehicle);

        VehicleDataHolder dataHolder = VehicleDataHolder.getInstance();

        //VehicleRecViewAdapter adapter = new VehicleRecViewAdapter(this);
        adapter = new VehicleRecViewAdapter(this);
        adapter.setVehicles(dataHolder.getVehicles());

        vehiclesRecyclerView.setAdapter(adapter);
        vehiclesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateVehicleActivity.class);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Vehicle newVehicle = intent.getParcelableExtra("newVehicle");
            if (newVehicle != null) {
                dataHolder.addVehicle(newVehicle);
                adapter.notifyDataSetChanged();
            }
        }
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

    public void addVehicle(Vehicle newVehicle) {    // deprecated
        vehicles.add(newVehicle);
    }
}