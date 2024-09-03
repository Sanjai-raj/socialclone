package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StoryAddActivity extends AppCompatActivity {


    ImageView homeBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_add);
        homeBack = findViewById(R.id.homeBack);


        //===============================================================================
        //===============================================================================
        //===============================================================================


        //==========================
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(StoryAddActivity.this,R.color.black));// set status background white
        //==========================




        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                finish();

            }
        });



    }
}