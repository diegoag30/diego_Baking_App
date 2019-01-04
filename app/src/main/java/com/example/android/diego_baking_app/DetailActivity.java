package com.example.android.diego_baking_app;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.diego_baking_app.Objects.Recipe;

public class DetailActivity extends AppCompatActivity {
    RecyclerView detail_rv;
    LinearLayoutManager linearLM;

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
