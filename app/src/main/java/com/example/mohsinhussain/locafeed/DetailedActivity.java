package com.example.mohsinhussain.locafeed;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        CommentFragment fragment2 = new CommentFragment();
        fragment2.setPost_id(user_id);

        //String title = intent.getStringExtra("title");
  //      String description = intent.getStringExtra("description");
//        String votes = intent.getStringExtra("votes");

        //  String title = intent.getStringExtra("description_text");
       // String description = intent.getStringExtra("detail_description");
       // String votes = intent.getStringExtra("votes");


        TextView myAwesomeTextView = (TextView)findViewById(R.id.descriptionText);
        TextView myAwesomeTextView1 = (TextView)findViewById(R.id.detail_description);

        TextView myAwesomeTextView2 = (TextView)findViewById(R.id.tx_votes);



       // myAwesomeTextView.setText(title);
      //  myAwesomeTextView1.setText(description);
       // myAwesomeTextView2.setText(votes);

        Toast.makeText(this, user_id, Toast.LENGTH_LONG).show();

    }



}

