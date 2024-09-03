package com.example.instagram;

import static com.example.instagram.R.id.proFile_recyclerView;
import static com.example.instagram.R.id.story_add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {


    TabLayout tabLayout;
    RecyclerView proFile_recyclerView;
    ImageView profilePic;
    TextView Profile_TotalPost, Profile_TotalFollowers, Profile_TotalFollowing, idName, profileBio, editProfile, shareProfile;
    LinearLayout addFriands, proStory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_profile, container, false);
        container.removeAllViews();

        tabLayout = myView.findViewById(R.id.tabLayout);
        proFile_recyclerView = myView.findViewById(R.id.proFile_recyclerView);
        profilePic = myView.findViewById(R.id.profilePic);
        Profile_TotalPost = myView.findViewById(R.id.Profile_TotalPost);
        Profile_TotalFollowers = myView.findViewById(R.id.Profile_TotalFollowers);
        Profile_TotalFollowing = myView.findViewById(R.id.Profile_TotalFollowing);
        idName = myView.findViewById(R.id.idName);
        profileBio = myView.findViewById(R.id.profileBio);
        editProfile = myView.findViewById(R.id.editProfile);
        shareProfile = myView.findViewById(R.id.shareProfile);
        addFriands = myView.findViewById(R.id.addFriands);
        proStory = myView.findViewById(R.id.proStory);



        //=================================================================================
        //=================================================================================
        //=================================================================================






        //===================
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(getContext(), "Edit Profile", Toast.LENGTH_SHORT).show();
            }
        });

        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(getContext(), "Share Profile", Toast.LENGTH_SHORT).show();
            }
        });

        addFriands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(getContext(), "Add Friands", Toast.LENGTH_SHORT).show();
            }
        });
        
        proStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(getContext(), "Story Add", Toast.LENGTH_SHORT).show();
            }
        });
        //===================





        picUpAdapter adapter = new picUpAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        proFile_recyclerView.setLayoutManager(gridLayoutManager);
        proFile_recyclerView.setAdapter(adapter);

        //==========================
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //

                int tabPosition = tab.getPosition();

                if (tabPosition==0){
                    //----
                    picUpAdapter adapter = new picUpAdapter();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    proFile_recyclerView.setLayoutManager(gridLayoutManager);
                    proFile_recyclerView.setAdapter(adapter);

                }
                else if (tabPosition==1) {
                    //----
                    reelsUpAdapter adapter = new reelsUpAdapter();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    proFile_recyclerView.setLayoutManager(gridLayoutManager);
                    proFile_recyclerView.setAdapter(adapter);

                }
                else if (tabPosition==2) {
                    //----
                    UpTagAdapter adapter = new UpTagAdapter();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    proFile_recyclerView.setLayoutManager(gridLayoutManager);
                    proFile_recyclerView.setAdapter(adapter);

                }

                //
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });






        return myView;
    }//onCreateView end.......

    //Pic up adapter===============================
    private class picUpAdapter extends RecyclerView.Adapter <picUpAdapter.picUpAdapterViewHolder> {

        @NonNull
        @Override
        public picUpAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //layout View------
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.profile_up_pic_items, null);

            return new picUpAdapterViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull picUpAdapterViewHolder holder, int position) {
            //loyout code hear......

        }

        @Override
        public int getItemCount() {
            return 41;
        }

        private class picUpAdapterViewHolder extends RecyclerView.ViewHolder {
            public picUpAdapterViewHolder(@NonNull View itemView) {super(itemView);
                //layout all id hear------


            }
        }

    }
    //===================================


    //Reels up adapter==============================
    private class reelsUpAdapter extends RecyclerView.Adapter <reelsUpAdapter.reelsUpAdapterViewHolder> {

        @NonNull
        @Override
        public reelsUpAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //layout view hear......
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.profile_up_reels_items, null);

            return new reelsUpAdapterViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull reelsUpAdapterViewHolder holder, int position) {
            //layout code hear......

        }

        @Override
        public int getItemCount() {
            return 7;
        }

        private class reelsUpAdapterViewHolder extends RecyclerView.ViewHolder {
            public reelsUpAdapterViewHolder(@NonNull View itemView) {super(itemView);
                //layout all id integration hear.....

            }
        }

    }
    //===================================


    //Up tag adapter================================
    private class UpTagAdapter extends RecyclerView.Adapter <UpTagAdapter.UpTagAdapterViewHolder> {

        @NonNull
        @Override
        public UpTagAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //layout view inflate hear.....
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.profile_up_tag_items, null);

            return new UpTagAdapterViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull UpTagAdapterViewHolder holder, int position) {
            //layout code hear.....

        }

        @Override
        public int getItemCount() {
            return 14;
        }

        private class UpTagAdapterViewHolder extends RecyclerView.ViewHolder {
            public UpTagAdapterViewHolder(@NonNull View itemView) {super(itemView);
                //layout all id integretion hear.......


            }
        }

    }
    //===================================



}