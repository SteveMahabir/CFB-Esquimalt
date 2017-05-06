package com.example.steve.basedirectory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapterUnit extends RecyclerView.Adapter<RecycleViewAdapterUnit.UnitViewHolder> {

    ArrayList<Unit> units;

    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView unitName;
        TextView unitPhoneNo;
        ImageView unitPhoto;

        UnitViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            //unitName = (TextView) itemView.findViewById(R.id.unit_name);
            //unitPhoneNo = (TextView) itemView.findViewById(R.id.unit_phone_no);
            //unitPhoto = (ImageView) itemView.findViewById(R.id.unit_photo);
        }
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_units, viewGroup, false);
        UnitViewHolder cvh = new UnitViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(UnitViewHolder unitViewHolder, int i) {
        unitViewHolder.unitName.setText(units.get(i).UnitName);
        unitViewHolder.unitPhoneNo.setText(units.get(i).UnitTelephone);
        unitViewHolder.unitPhoto.setImageResource(units.get(i).UnitPictureId);
    }

    @Override
    public int getItemCount() {
        return units.size();
    }





}

