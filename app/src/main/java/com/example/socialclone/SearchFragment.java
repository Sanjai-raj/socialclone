package com.example.instagram;

import static com.example.instagram.R.id.gridView;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchFragment extends Fragment {

    public static String SrcImg1 = "";
    public static int arraylist = 0;

    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_search, container, false);
        container.removeAllViews();
        gridView = myView.findViewById(R.id.gridView);



        //method call-----------
        myAdapter adapter = new myAdapter();
        gridView.setAdapter(adapter);
        //----------------------






        return myView;
    }//onCreateView end.....


    //search sec item gridView helper adapter=================
    private class myAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arraylist;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.search_items, null);
            ImageView searchImg = myView.findViewById(R.id.searchImg);


            Picasso.get().load(SrcImg1).placeholder(R.drawable.loading_post).into(searchImg);



            return myView;
        }
    }
    //========================================================



}