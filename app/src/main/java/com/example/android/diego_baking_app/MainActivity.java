package com.example.android.diego_baking_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.diego_baking_app.Objects.Recipe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipesItems;
    private TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_tv = (TextView)findViewById(R.id.main_tv);
        setPopularity();
    }

    public void setPopularity(){
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
                            assert recipesItems != null;
                            main_tv.setText(recipesItems.get(0).getRecipeName());
                        }
                    });
                }
            });

    }
}
