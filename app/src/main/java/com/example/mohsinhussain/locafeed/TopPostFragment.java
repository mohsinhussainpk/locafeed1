package com.example.mohsinhussain.locafeed;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopPostFragment extends Fragment implements AdapterView.OnItemClickListener {

    String json_string;
   JSONObject jsonObject;
    JSONArray jsonArray;
    PostsAdapter postsAdapter;
    ListView listView;
  public TopPostFragment(){


  }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_top_post,container,false);

        TopBackgroundTask yat=new TopBackgroundTask(getActivity());
        new TopBackgroundTask(getActivity().getApplicationContext()).execute();

        String[] menuItems = {"do something", "did something"};
        listView =(ListView) view.findViewById(R.id.topListView);
/*
        listView =(ListView) view.findViewById(R.id.topListView);
       postsAdapter = new PostsAdapter(getActivity(),R.layout.custom_row);
        listView.setAdapter(postsAdapter);

       // json_string = yat.JSONSTRING;


        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("post");


            int count= 0;
            String title, description, votes;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                title = JO.getString("post_title");
                description = JO.getString("post_description");
                votes = JO.getString("post_votes");

                Posts posts = new Posts(title,description,votes);
                postsAdapter.add(posts);

                count++;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(

                getActivity(),android.R.layout.simple_list_item_1, menuItems
        );

        listView.setAdapter(listViewAdapter);








        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* TopBackgroundTask yat=new TopBackgroundTask(getActivity().getApplicationContext());

        new TopBackgroundTask(getActivity().getApplicationContext()).execute();

        String[] menuItems = {"do something", "did something"};


        postsAdapter = new PostsAdapter(getActivity().getApplicationContext(),R.layout.custom_row);
        listView.setAdapter(postsAdapter);

        // json_string = yat.JSONSTRING;
        try {
            json_string=yat.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("post");


            int count= 0;
            String title, description, votes;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                title = JO.getString("post_title");
                description = JO.getString("post_description");
                votes = JO.getString("post_votes");

                Posts posts = new Posts(title,description,votes);
                postsAdapter.add(posts);

                count++;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

}
