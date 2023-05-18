package com.example.wartungsapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehicleRecViewAdapter extends RecyclerView.Adapter<VehicleRecViewAdapter.ViewHolder>{

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public VehicleRecViewAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtVehicleName.setText(vehicles.get(position).getName());
        holder.imgVehicle.setImageURI(Uri.parse(vehicles.get(position).getImageURL()));
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    //to set initial data from main activity
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
        notifyDataSetChanged();     //to refresh on changes
    }

    // this class is going to hold the view for every item in the RecView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView vehicleCard;
        private TextView txtVehicleName;
        private ImageView imgVehicle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleCard = itemView.findViewById(R.id.vehicleCard);
            txtVehicleName = itemView.findViewById(R.id.txtVehicleName);
            imgVehicle = itemView.findViewById(R.id.imgVehicle);
        }
    }
}