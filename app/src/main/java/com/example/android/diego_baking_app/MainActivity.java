package com.example.android.diego_baking_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.diego_baking_app.Objects.Recipe;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipesItems;
    private TextView main_tv;
    RecyclerView main_rv;
    ProgressBar centerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_rv = (RecyclerView)findViewById(R.id.main_recyclerview);
        centerProgressBar = (ProgressBar) findViewById(R.id.center_progressbar);
        cardRequest();
    }

    public void cardRequest(){
            String myUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
            /* responseToString(popularityString,mAdapter,moviesItems);*/
            OkHttpClient myClient = new OkHttpClient();
            Request myRequest = new Request.Builder()
                    .url(myUrl)
                    .build();
            myClient.newCall(myRequest).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String myResponse = response.body().string();
                    if(!response.isSuccessful()) {
                        throw new IOException();
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Instantiate the adapter
                            recipesItems = jsonParse.RecipeParser(myResponse);

                            // Creating and setting the adapter
                            CardAdapter cardAdapter = new CardAdapter(recipesItems);
                            centerProgressBar.setVisibility(View.GONE);
                            main_rv.setAdapter(cardAdapter);
                            main_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            main_rv.setVisibility(View.VISIBLE);
/*                            assert recipesItems != null;
                            main_tv.setText(recipesItems.get(0).getRecipeName());*/
                        }
                    });
                }
            });

    }
}
