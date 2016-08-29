package com.example.mohsinhussain.locafeed;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {


    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CommentsAdapter commentsAdapter;
   // PostsAdapter postsAdapter;
    ListView listView;

    public static String post_id;


    public void setPost_id(String string){
        post_id = string;







    }
    public CommentFragment() {
        // Required empty public constructor
    }

    public static String location;
    public void setName(String string){
       // category = string;







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

            GetCommentsTask toptask = new GetCommentsTask();
            //TopBackgroundTask1 toptask = new TopBackgroundTask1();
            toptask.execute();

            Intent i = new Intent(getActivity(), DetailedActivity.class);
// set the new task and clear flags
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            //  startActivity(new Intent(getActivity(), Main2Activity.class));


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comment2,container,false);

        GetCommentsTask toptask = new GetCommentsTask();
        //TopBackgroundTask1 toptask = new TopBackgroundTask1();
        toptask.execute();

        try {
            json_string = toptask.get();
            Log.v("log",json_string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //   String[] menuItems = {"do something", "did something"};
        listView =(ListView) view.findViewById(R.id.commentListView);

        commentsAdapter = new CommentsAdapter(getActivity(),R.layout.comment_row);
        listView.setAdapter(commentsAdapter);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("comment");


            int count= 0;
            String comment;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                comment = JO.getString("comment");
            //    description = JO.getString("post_description");
             //   votes = JO.getString("post_votes");
              //  userid = JO.getString("post_id");


                Comments comments = new Comments(comment);
 //               Posts posts = new Posts(comment);
              //  postsAdapter.add(posts);
                commentsAdapter.add(comments);

                count++;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // listView =(ListView) view.findViewById(R.id.topListView);

        return view;
    }



    public class GetCommentsTask extends AsyncTask<String,Void,String> {

        private final String LOG_TAG = GetCommentsTask.class.getSimpleName();
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
                        .appendPath("get_comments.php")
                        .appendQueryParameter("post_id", post_id);

                // .appendQueryParameter("sort", "relevance")
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
}
