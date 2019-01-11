package com.example.android.diego_baking_app;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.diego_baking_app.Objects.ExoPlayerFragment;

public class StepDetail extends AppCompatActivity {
    private boolean videoFragmentCreated;
    private static final String VIDEO_FRAGMENT_CREATED = "VIDEO FRAGMENT CREATED";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        if (savedInstanceState!= null){
            videoFragmentCreated = savedInstanceState.getBoolean(VIDEO_FRAGMENT_CREATED);
        }
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (!videoFragmentCreated) {
                exoPlayerUi();
                videoFragmentCreated = true;
            }
        }else {
                instructionUi();
            if (!videoFragmentCreated) {
                exoPlayerUi();
                videoFragmentCreated = true;
            }
        }

    }
    public  void instructionUi(){
        String instruction = getIntent().getStringExtra("STEP_INSTRUCTION");
        StepFragment stepFragment = new StepFragment();
        stepFragment.set_Text(instruction);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().
                replace(R.id.step_instruction,stepFragment)
                .commit();
    }

    public void exoPlayerUi(){
        String videoLink = getIntent().getStringExtra("VIDEO_LINK");
        Bundle bundle = new Bundle();
        bundle.putString("VIDEO_LINK",videoLink);
        ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
        exoPlayerFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().
                replace(R.id.video_player,exoPlayerFragment)
                .commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(VIDEO_FRAGMENT_CREATED,videoFragmentCreated);
    }
}
