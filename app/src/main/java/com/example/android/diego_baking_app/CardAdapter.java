package com.example.android.diego_baking_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.diego_baking_app.Objects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_cv;
    TextView recipe_title;
    ImageView recipe_imageView;

    CardViewHolder(View itemView) {
        super(itemView);
        recipe_cv = (CardView)itemView.findViewById(R.id.recipe_card_view);
        recipe_title = (TextView)itemView.findViewById(R.id.recipe_title_tv);
        recipe_imageView = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }

    ArrayList<Recipe> recipes;

    public CardAdapter(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
