package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MessagesActivity extends AppCompatActivity {


    ImageView goBack, videoCall, editMsg;
    TextView idName;
    SearchView searchbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        goBack = findViewById(R.id.goBack);
        videoCall = findViewById(R.id.videoCall);
        editMsg = findViewById(R.id.editMsg);
        idName = findViewById(R.id.idName);
        searchbar = findViewById(R.id.searchbar);


        //================================================================================
        //================================================================================
        //================================================================================



        //==========================
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(MessagesActivity.this,R.color.white));// set status background white
        //==========================



        //===========
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                finish();

            }
        });





    }
}