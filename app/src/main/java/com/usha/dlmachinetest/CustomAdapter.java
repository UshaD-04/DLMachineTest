package com.usha.dlmachinetest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.locViewHolder> {
    List<MainModel.Venue> venuesArrayList;
    Context mContext;
    SaveAddress saveAddress;

    public CustomAdapter(List<MainModel.Venue> venusData, Context context) {
        this.mContext = context;
        this.venuesArrayList = venusData;
    }

    public void setOnDataChangeListener(SaveAddress saveAddress) {
        this.saveAddress = saveAddress;
    }

    @Override
    public CustomAdapter.locViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lists, parent, false);
        return new locViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.locViewHolder holder, int position) {
        MainModel.Venue venuesData = venuesArrayList.get(position);
        holder.locName.setText(venuesData.getName());
        if (venuesData.getLocation() != null) {
            holder.distance.setText(String.valueOf(venuesData.getLocation().getDistance()));
            holder.address.setText(venuesData.getLocation().getFormattedAddress().toString());
        }
        holder.phonenumber.setText(venuesData.getContact().getPhone());
        if (venuesData.getIsAddressSaved() == 0) {
            holder.image_star.setVisibility(View.VISIBLE);
            holder.image_star_filled.setVisibility(View.GONE);

        } else {
            holder.image_star_filled.setVisibility(View.VISIBLE);
            holder.image_star.setVisibility(View.GONE);

        }

        holder.image_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveAddress != null) {
                    venuesData.setAddressSaved(1);
                    notifyItemChanged(position);
                    saveAddress.onDataChanged(venuesData, venuesData.getIsAddressSaved(),
                            venuesData.getId(), position);

                }
            }
        });
        holder.image_star_filled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveAddress != null) {
                    venuesData.setAddressSaved(0);
                    notifyItemChanged(position);
                    saveAddress.onDataChanged(venuesData, venuesData.getIsAddressSaved(),
                            venuesData.getId(), position);
                    venuesArrayList.remove(position);
                    notifyDataSetChanged();

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return venuesArrayList.size();
    }

    public class locViewHolder extends RecyclerView.ViewHolder {
        TextView locName;
        TextView distance;
        TextView address;
        TextView phonenumber;
        ImageView image_star;
        ImageView image_star_filled;

        public locViewHolder(View itemView) {
            super(itemView);
            locName = itemView.findViewById(R.id.tvName);
            distance = itemView.findViewById(R.id.distanceTextView);
            address = itemView.findViewById(R.id.addressTextview);
            phonenumber = itemView.findViewById(R.id.tv_call);
            image_star = itemView.findViewById(R.id.img_star);
            image_star_filled = itemView.findViewById(R.id.img_star_filled);
        }
    }
}
