package com.example.android.diego_baking_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.diego_baking_app.Objects.Recipe;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_cv;
    TextView recipe_title;
    ImageView recipe_imageView;

    CardViewHolder(View itemView) {
        super(itemView);
        /*recipe_cv = (CardView)itemView.findViewById(R.id.recipe_card_view);*/
        recipe_title = (TextView)itemView.findViewById(R.id.recipe_title_tv);
        recipe_imageView = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }

    private ArrayList<Recipe> recipes;

    public CardAdapter(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.card_views,viewGroup,false);

        CardViewHolder cardViewHolder = new CardViewHolder(recipeView);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        final Recipe recipe = recipes.get(i);

        //Title binding
        TextView recipeTitle = cardViewHolder.recipe_title;
        recipeTitle.setText(recipe.getRecipeName());

        //Image binding
        ImageView recipeImage = cardViewHolder.recipe_imageView;
        String checkImage = recipe.getCardImage();

        //If the images is null we set a default image.
        if(!checkImage.equals("")){
            Picasso.with(cardViewHolder.recipe_imageView.getContext())
                    .load(checkImage)
                    .into(recipeImage);
        } else {
            recipeImage.setImageResource(R.drawable.unavailable_image);
        }
        cardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DetailActivity.class);
                intent.putExtra("card",recipe);
                SharedPreferences sPreferences = PreferenceManager.getDefaultSharedPreferences
                        (view.getContext().getApplicationContext());
                SharedPreferences.Editor editor = sPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(recipe);
                editor.putString("RECIPE",json);
                editor.commit();
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
