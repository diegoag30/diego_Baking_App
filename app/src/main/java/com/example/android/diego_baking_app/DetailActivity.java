package com.example.android.diego_baking_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.diego_baking_app.Objects.Recipe;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Recipe recipe = getIntent().getParcelableExtra("card");
    }
}
