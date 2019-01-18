package com.example.android.diego_baking_app;

import android.os.Parcelable;
import android.support.annotation.NonNull;
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
    public final String ADAPTER_POSITION = "ADAPTER_POSITION";
    public static final String RECIPES_LIST = "RECIPES_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_rv = (RecyclerView)findViewById(R.id.main_recyclerview);
        main_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    assert response.body() != null;
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
                            main_rv.setAdapter(cardAdapter);

                        }
                    });
                }
            });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        RecyclerView.LayoutManager layoutManager = main_rv.getLayoutManager();
        assert layoutManager != null;
        outState.putParcelable(ADAPTER_POSITION,main_rv.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!= null){
            Parcelable savedAdapterState = savedInstanceState.getParcelable(ADAPTER_POSITION);
            RecyclerView.LayoutManager layoutManager = main_rv.getLayoutManager();
            assert layoutManager != null;
            layoutManager.onRestoreInstanceState(savedAdapterState);
        }
    }
}
