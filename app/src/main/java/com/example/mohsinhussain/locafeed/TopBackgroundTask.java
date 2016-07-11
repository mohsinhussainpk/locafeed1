package com.example.mohsinhussain.locafeed;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


/**
 * Created by mohsinhussain on 10/07/2016.
 */
public class TopBackgroundTask extends AsyncTask<Void,Void,String> {

    String JSONSTRING;
    Context ctx;

    TopBackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "https://evening-cove-67540.herokuapp.com/get_topposts.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSONSTRING=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSONSTRING+"\n");


                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            JSONSTRING = result;

            if(JSONSTRING==null)
            {

                Toast.makeText(ctx, "First Get JSON", Toast.LENGTH_LONG).show();
            }
            else
            {

                Toast.makeText(ctx, "JSON", Toast.LENGTH_LONG).show();



         /*   Intent intent = new Intent(ctx, TopPostFragment.class);
            intent.putExtra("json_data",JSONSTRING);
            startAcivity(intent);
              */





           /*
            try {

                jsonObject = new JSONObject(JSONSTRING);
                jsonArray = jsonObject.getJSONArray("post");

                int count=0;

               String title, description, votes;
                while(count<jsonArray.length())
                {

                    JSONObject JO = jsonArray.getJSONObject(count);
                    title = JO.getString("post_title");
                    description = JO.getString("post_description");
                    votes = JO.getString("post_votes");

                    Posts posts = new Posts(title,description,votes);
                   // customAdapter.add(posts);

                    count++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


                  */
            }


        }


}

