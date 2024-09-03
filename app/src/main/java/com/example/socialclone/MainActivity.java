package com.example.instagram;

import static com.example.instagram.R.id.coordinatorLayout;
import static com.example.instagram.R.id.splashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static int NUM_OF_POST = 0;
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    CoordinatorLayout CoordinatorLayout;
    DrawerLayout drawerLayout;
    AppBarLayout appBarHome, appBarProfile, appBarSearch;
    LinearLayout home_toolBar;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    ImageView igLogo, notification, messege, post;
    BottomNavigationView bottomNavigation;
    RelativeLayout splashScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        CoordinatorLayout = findViewById(R.id.coordinatorLayout);
        appBarHome = findViewById(R.id.appBarHome);
        appBarProfile = findViewById(R.id.appBarProfile);
        appBarSearch = findViewById(R.id.appBarSearch);
        home_toolBar = findViewById(R.id.home_toolBar);
        materialToolbar = findViewById(R.id.materialToolbar);
        navigationView = findViewById(R.id.navigationView);
        igLogo = findViewById(R.id.igLogo);
        notification = findViewById(R.id.notification);
        messege = findViewById(R.id.messege);
        post = findViewById(R.id.post);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        splashScreen = findViewById(R.id.splashScreen);


        //==========================
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));// set status background white
        //==========================



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (SPLASH_DISPLAY_LENGTH==1000){

                    splashScreen.setVisibility(View.GONE);
                    CoordinatorLayout.setVisibility(View.VISIBLE);
                    navigationView.setVisibility(View.VISIBLE);

                }

            }
        }, SPLASH_DISPLAY_LENGTH);



        //===========================================================
        //===========================================================
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isAvailable() || !networkInfo.isConnected()){

            Dialog dialog = new Dialog(MainActivity.this);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations
                    = android.R.style.Animation_Dialog;
            dialog.setContentView(R.layout.no_internet);
            ImageView try_agin = dialog.findViewById(R.id.try_agin);
            try_agin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recreate();

                }
            });
            dialog.setCancelable(false);
            dialog.show();


        }




        //======================================================================
        //======================================================================
        //======================================================================




        //navigation drawer=========
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(drawerToggle);
        //=========================




        //toolbar edit==================
        messege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MessagesActivity.class));
            }
        });
        
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                showBottomDialog();
            }
        });
        //==============================




        //View Desplay==========================
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        bottomNavigation.getOrCreateBadge(R.id.home).setNumber(NUM_OF_POST);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //~~~~~~~~~~~~~~~~~~~~~~~~~
                if (item.getItemId()==R.id.home){
                    //
                    appBarHome.setVisibility(View.VISIBLE);
                    appBarProfile.setVisibility(View.GONE);
                    appBarSearch.setVisibility(View.GONE);
                    //---------------

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
                    fragmentTransaction.commit();
                    bottomNavigation.getOrCreateBadge(R.id.home).clearNumber();
                    //
                }
                else if (item.getItemId()==R.id.search) {
                    //
                    appBarSearch.setVisibility(View.VISIBLE);
                    appBarHome.setVisibility(View.GONE);
                    appBarProfile.setVisibility(View.GONE);
                    //----------------

                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1.add(R.id.frameLayout, new SearchFragment());
                    fragmentTransaction1.commit();
                }
                else if (item.getItemId()==R.id.post) {
                    //
                    showBottomDialog();
                    //
                }
                else if (item.getItemId()==R.id.reels) {
                    //
                    appBarHome.setVisibility(View.VISIBLE);
                    appBarProfile.setVisibility(View.GONE);
                    appBarSearch.setVisibility(View.GONE);
                    //------------

                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                    fragmentTransaction3.add(R.id.frameLayout, new ReelsFragment());
                    fragmentTransaction3.commit();

                }
                else if (item.getItemId()==R.id.profile) {
                    //
                    appBarProfile.setVisibility(View.VISIBLE);
                    appBarHome.setVisibility(View.GONE);
                    appBarSearch.setVisibility(View.GONE);
                    //-----------

                    FragmentManager fragmentManager4 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                    fragmentTransaction4.add(R.id.frameLayout, new ProfileFragment());
                    fragmentTransaction4.commit();
                }


                return true;
            }
        });
        //==========================






    }//onCreate Bundle end....

    //showBottomDialog======================
    private void showBottomDialog () {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomshetelayout);

        LinearLayout layoutReel = dialog.findViewById(R.id.layoutReel);
        LinearLayout layoutPost = dialog.findViewById(R.id.layoutPost);
        LinearLayout layoutStory = dialog.findViewById(R.id.layoutStory);
        LinearLayout layoutStory_high = dialog.findViewById(R.id.layoutStory_high);
        LinearLayout layoutLive = dialog.findViewById(R.id.layoutLive);
        LinearLayout layoutGuide = dialog.findViewById(R.id.layoutGuide);
        
        layoutReel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload a Reel is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layoutPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Post is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layoutStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Story is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layoutStory_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Story Highlight is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layoutLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Live is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layoutGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Guide is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }
    //======================================





}