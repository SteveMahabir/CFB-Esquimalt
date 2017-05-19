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

    RecycleViewAdapterUnit(ArrayList<Unit> units){
        this.units = units;
    }

    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        CardView unitCardView;
        TextView unitName;
        TextView unitPhoneNo;
        ImageView unitPhoto;

        UnitViewHolder(View itemView) {
            super(itemView);
            unitCardView = (CardView) itemView.findViewById(R.id.card_view);
            unitName = (TextView) itemView.findViewById(R.id.cv_text_main);
            unitPhoneNo = (TextView) itemView.findViewById(R.id.cv_text_secondary);
            unitPhoto = (ImageView) itemView.findViewById(R.id.cv_image);
        }
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        v.setOnClickListener(UnitsActivity.unitClickListener);
        UnitViewHolder uvh = new UnitViewHolder(v);
        return uvh;
    }

    @Override
    public void onBindViewHolder(UnitViewHolder unitViewHolder, int i) {
        unitViewHolder.unitName.setText(units.get(i).UnitName);
        unitViewHolder.unitPhoneNo.setText(units.get(i).UnitTelephone);
        unitViewHolder.unitPhoto.setImageResource(units.get(i).UnitPictureId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return units.size();
    }





}

