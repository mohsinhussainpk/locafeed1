package com.example.mohsinhussain.locafeed;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    PostsAdapter postsAdapter;
    ListView listView;

    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        String post_id = getArguments().getString("post_id");
        String title = getArguments().getString("title");
        String description = getArguments().getString("description");
        String votes = getArguments().getString("votes");

        Toast.makeText(getActivity(), votes, Toast.LENGTH_LONG).show();

        return inflater.inflate(R.layout.fragment_comment, container, false);
        //Intent intent = ((Activity) context).getIntent();



       // Intent intent = getIntent();
/*
        Bundle extras = intent.getExtras();
        String username_string = extras.getString("EXTRA_USERNAME");
        String password_string = extras.getString("EXTRA_PASSWORD");
        */
    }

}
