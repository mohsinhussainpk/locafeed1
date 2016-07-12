package com.example.mohsinhussain.locafeed;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.topmenu_fragment, menu);
       }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.refresh_settings){

            TopBackgroundTask1 toptask = new TopBackgroundTask1();
            //TopBackgroundTask1 toptask = new TopBackgroundTask1();
           toptask.execute();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_top_post,container,false);

       // TopBackgroundTask yat=new TopBackgroundTask(getActivity());
      //  new TopBackgroundTask(getActivity().getApplicationContext()).execute();
        //String json;
         TopBackgroundTask1 yat = new TopBackgroundTask1();
        yat.execute();
        try {
            json_string = yat.get();
            Log.v("log",json_string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
     //   String[] menuItems = {"do something", "did something"};
        listView =(ListView) view.findViewById(R.id.topListView);

       // listView =(ListView) view.findViewById(R.id.topListView);
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

/*
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(

                getActivity(),android.R.layout.simple_list_item_1, menuItems
        );

        listView.setAdapter(listViewAdapter);
*/







        return view;
    }

    public class TopBackgroundTask1 extends AsyncTask<String,Void,String> {

        private final String LOG_TAG = TopBackgroundTask1.class.getSimpleName();
        @Override
        protected String doInBackground(String... params) {

            // These two need to be declared outside the try/catchforecastJsonStr
// so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

// Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("evening-cove-67540.herokuapp.com")
                        .appendPath("get_topposts3.php")
                        .appendQueryParameter("cat", "event");
                        //.appendQueryParameter("sort", "relevance")
                        //.fragment("section-name");
                String myUrl = builder.build().toString();
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are available at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
               // URL url = new URL("https://evening-cove-67540.herokuapp.com/get_topposts.php");

                URL url = new URL(myUrl);

                Log.v(LOG_TAG,"Built Uri"+ myUrl );

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    forecastJsonStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");

                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    forecastJsonStr = null;
                }
                forecastJsonStr = buffer.toString();
               // Log.v(LOG_TAG,"Top post json string: " + forecastJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                forecastJsonStr = null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return forecastJsonStr;
        }
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
