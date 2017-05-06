package com.example.steve.basedirectory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapterCategory extends RecyclerView.Adapter<RecycleViewAdapterCategory.CategoryViewHolder> {

    ArrayList<Category> categories;

    RecycleViewAdapterCategory(ArrayList<Category> categories){
        this.categories = categories;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView categoryName;
        ImageView categoryPhoto;

        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            categoryPhoto = (ImageView) itemView.findViewById(R.id.cv_image);
            categoryName = (TextView) itemView.findViewById(R.id.cv_text_main);
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        v = (RecyclerView) v.findViewById(android.R.id.cv);
        CategoryViewHolder cvh = new CategoryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.categoryName.setText(categories.get(i).CategoryType);
        categoryViewHolder.categoryPhoto.setImageResource(categories.get(i).CategoryPicutreId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }




}

