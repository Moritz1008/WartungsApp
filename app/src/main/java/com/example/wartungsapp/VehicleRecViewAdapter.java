package com.example.wartungsapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VehicleRecViewAdapter extends RecyclerView.Adapter<VehicleRecViewAdapter.ViewHolder>{

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    private Context context;

    public VehicleRecViewAdapter(Context context) {
        this.context = context;
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
        holder.txtVehicleMileage.setText(String.valueOf(vehicles.get(position).getMileage()) + " km");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, vehicles.get(holder.getAbsoluteAdapterPosition()).getName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(vehicles.get(position).getImageURI())
                .into(holder.imgVehicle);
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

        private TextView txtVehicleName;
        private TextView txtVehicleMileage;
        private ImageView imgVehicle;
        private CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVehicleName = itemView.findViewById(R.id.txtVehicleName);
            imgVehicle = itemView.findViewById(R.id.imgVehicle);
            card = itemView.findViewById(R.id.vehicleCard);
            txtVehicleMileage = itemView.findViewById(R.id.txtVehicleMileage);
        }
    }
}
