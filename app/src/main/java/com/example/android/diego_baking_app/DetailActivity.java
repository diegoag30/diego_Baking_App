package com.example.android.diego_baking_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.diego_baking_app.Objects.ExoPlayerFragment;
import com.example.android.diego_baking_app.Objects.Ingredients;
import com.example.android.diego_baking_app.Objects.Recipe;
import com.example.android.diego_baking_app.Widget.RecipeUpdateService;
import com.google.android.exoplayer2.Player;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView detail_rv;
    LinearLayoutManager linearLM;
    public static final String RECIPE_INGREDIENTS = "RECIPES_INGREDIENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Recipe recipe = getIntent().getParcelableExtra("card");
        setDetailUi(recipe);

    }
    public void setDetailUi(Recipe passedRecipe){
        detail_rv = (RecyclerView)findViewById(R.id.detail_rv);
        linearLM = new LinearLayoutManager(this);
        DetailAdapter dAdapter = new DetailAdapter(this,passedRecipe.getIngredients(),
                passedRecipe.getSteps());
        detail_rv.setLayoutManager(linearLM);
        detail_rv.setAdapter(dAdapter);
    }

}
