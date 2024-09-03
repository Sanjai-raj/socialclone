package com.example.instagram;

import static com.example.instagram.R.id.UpStory;
import static com.example.instagram.R.id.idName;
import static com.example.instagram.R.id.like;
import static com.example.instagram.R.id.moreOption;
import static com.example.instagram.R.id.post_RecyclerView;
import static com.example.instagram.R.id.profilePic;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {


    RecyclerView story_RecyclerView, post_RecyclerView;
    LinearLayout story_add;
    ProgressBar progressBar;
    ArrayList < HashMap <String, String> > arrayList = new ArrayList();
    HashMap <String, String> hashMap ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        container.removeAllViews();
        story_RecyclerView = myView.findViewById(R.id.story_RecyclerView);
        post_RecyclerView = myView.findViewById(R.id.post_RecyclerView);
        story_add = myView.findViewById(R.id.story_add);
        progressBar = myView.findViewById(R.id.progressBar);




        //========================================================================
        //========================================================================
        //========================================================================




        //method call=========================
        //server call
        postDataAdd();
        //method call end=====================







        //story sec====================
        story_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                startActivity(new Intent(getContext(), StoryAddActivity.class));
            }
        });












        return myView;
    }//onCreateView end....



    //story RecyclerView Adapter======================
    private class storyAdapter extends RecyclerView.Adapter <storyAdapter.story_ViewHolder> {

        @NonNull
        @Override
        public story_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //layout view infliment.......
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.story_item, null);

            return new story_ViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull story_ViewHolder holder, int position) {
            //layout code hear.......

            int getPosition = position;
            hashMap = arrayList.get(position);

            String profile_image = hashMap.get("profile_image");
            String account_name  = hashMap.get("account_name");


            Picasso.get().load(profile_image).into(holder.story_pic);
            holder.idName.setText(account_name);

            holder.story_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "your story "+getPosition, Toast.LENGTH_SHORT).show();
                }
            });
            
            
            

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class story_ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout UpStory;
            ImageView story_pic;
            TextView idName;

            public story_ViewHolder(@NonNull View itemView) {super(itemView);
                //layout all id integration hear......
                UpStory = itemView.findViewById(R.id.UpStory);
                story_pic = itemView.findViewById(R.id.story_pic);
                idName = itemView.findViewById(R.id.idName);
                
            }
        }

    }
    //=================================================



    //post RecyclerView Adapter======================
    private class postAdapter extends RecyclerView.Adapter <postAdapter.postViewHolder> {

        @NonNull
        @Override
        public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //layout view infliment.......
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.ig_post, null);

            return new postViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
            //layout code hear.....

            hashMap = arrayList.get(position);

            String id = hashMap.get("id");
            String profile_image = hashMap.get("profile_image");
            String account_name = hashMap.get("account_name");
            String num_of_img = hashMap.get("num_of_img");
            String image1 = hashMap.get("image1");
            String image2 = hashMap.get("image2");
            String like = hashMap.get("like");
            String comment = hashMap.get("comment");
            String tag = hashMap.get("tag");
            String caption = hashMap.get("caption");

            SearchFragment.SrcImg1 = image1;


            Picasso.get().load(profile_image).into(holder.profilePic);
            holder.idName.setText(account_name);
            holder.id_name.setText(account_name);
            holder.numOfImg.setText(num_of_img);


            //-----------
            ArrayList<SlideModel> imageList = new ArrayList<>();
            // imageList.add(SlideModel("String Url" or R.drawable)
            // imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

            imageList.add(new SlideModel(image1, null));
            imageList.add(new SlideModel(image2, null));
            holder.postImg_slider.setImageList(imageList, ScaleTypes.CENTER_CROP);
            //------------


            holder.numOfLikes.setText(like);
            holder.numOfComment.setText(comment);
            if (tag.length()>1){
                holder.postTag.setVisibility(View.VISIBLE);
                holder.postTag.setText(tag);
            }else {
                holder.postTag.setVisibility(View.GONE);
            }
            holder.caption_right.setText(caption);


            holder.moreOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    Toast.makeText(getContext(), "More", Toast.LENGTH_SHORT).show();
                }
            });

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    holder.like.setImageResource(R.drawable.love_fill);
                    Toast.makeText(getContext(), "Like", Toast.LENGTH_SHORT).show();
                }
            });

            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    Toast.makeText(getContext(), "Comment hear...", Toast.LENGTH_SHORT).show();
                }
            });

            holder.sheare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    Toast.makeText(getContext(), "Share Post", Toast.LENGTH_SHORT).show();
                }
            });

            holder.savePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    holder.savePost.setImageResource(R.drawable.bookmark_fill);
                    Toast.makeText(getContext(), "Save Post", Toast.LENGTH_SHORT).show();
                }
            });







        }

        @Override
        public int getItemCount() {
            int postCount = arrayList.size();
            MainActivity.NUM_OF_POST = postCount;
            SearchFragment.arraylist = postCount;
            return postCount;
        }

        private class postViewHolder extends RecyclerView.ViewHolder {

            ImageView profilePic, blueTik, moreOption, like, comment, sheare, savePost;
            ImageSlider postImg_slider;
            TextView idName, numOfImg, numOfLikes, postTag, numOfComment, last2Comment, id_name, caption_right;
            EditText commentBox;

            public postViewHolder(@NonNull View itemView) {
                super(itemView);

                //layout all id integration hear......
                profilePic = itemView.findViewById(R.id.profilePic);
                idName = itemView.findViewById(R.id.idName);
                blueTik = itemView.findViewById(R.id.blueTik);
                moreOption = itemView.findViewById(R.id.moreOption);
                numOfImg = itemView.findViewById(R.id.numOfImg);
                postImg_slider = itemView.findViewById(R.id.postImg_slider);
                like = itemView.findViewById(R.id.like);
                comment = itemView.findViewById(R.id.comment);
                sheare = itemView.findViewById(R.id.sheare);
                savePost = itemView.findViewById(R.id.savePost);
                numOfLikes = itemView.findViewById(R.id.numOfLikes);
                postTag = itemView.findViewById(R.id.postTag);
                numOfComment = itemView.findViewById(R.id.numOfComment);
                last2Comment = itemView.findViewById(R.id.last2Comment);
                commentBox = itemView.findViewById(R.id.commentBox);
                id_name = itemView.findViewById(R.id.id_name);
                caption_right = itemView.findViewById(R.id.caption_right);

            }
        }

    }
    //===============================================






    //server area=============================================================================
    //========================================================================================


    private void postDataAdd () {
        arrayList = new ArrayList<>();
        String url = "https://sakib71.000webhostapp.com/apps/instagram/get_data.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);
                for (int i=0; i<response.length(); i++){

                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String profile_image = jsonObject.getString("profile_image");
                        String account_name = jsonObject.getString("account_name");
                        String num_of_img = jsonObject.getString("num_of_img");
                        String image1 = jsonObject.getString("image1");
                        String image2 = jsonObject.getString("image2");
                        String like = jsonObject.getString("like");
                        String comment = jsonObject.getString("comment");
                        String tag = jsonObject.getString("tag");
                        String caption = jsonObject.getString("caption");


                        hashMap = new HashMap<>();
                        hashMap.put("id", id);
                        hashMap.put("profile_image", profile_image);
                        hashMap.put("account_name", account_name);
                        hashMap.put("num_of_img", num_of_img);
                        hashMap.put("image1", image1);
                        hashMap.put("image2", image2);
                        hashMap.put("like", like);
                        hashMap.put("comment", comment);
                        hashMap.put("tag", tag);
                        hashMap.put("caption", caption);
                        arrayList.add(hashMap);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }


                if (arrayList.size()>0){
                    //story call========
                    storyAdapter storyAdapter = new storyAdapter();
                    story_RecyclerView.setAdapter(storyAdapter);
                    story_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false ));

                    //post call========
                    postAdapter postAdapter = new postAdapter();
                    post_RecyclerView.setAdapter(postAdapter);
                    post_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);

    }



    //========================================================================================
    //========================================================================================
    //========================================================================================






}