package com.example.android.diego_baking_app;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.diego_baking_app.Objects.ExoPlayerFragment;

public class StepDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        instructionUi();
        exoPlayerUi();





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
        ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().
                replace(R.id.video_player,exoPlayerFragment)
                .commit();
        exoPlayerFragment.playVideo(videoLink);
    }
}
