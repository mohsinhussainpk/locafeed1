package com.example.mohsinhussain.locafeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    TabLayout tabLayout;
    String category;
   // String JSONSTRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);


        // Fragment manager to add fragment in viewpager we will pass object of Fragment manager to adpater class.
        FragmentManager manager=getSupportFragmentManager();

        //object of PagerAdapter passing fragment manager object as a parameter of constructor of PagerAdapter class.
        PagerAdapter adapter=new PagerAdapter(manager);

        //set Adapter to view pager
        pager.setAdapter(adapter);

        //set tablayout with viewpager
        tabLayout.setupWithViewPager(pager);

        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting tabs from adpater
        tabLayout.setTabsFromPagerAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setContentView(R.layout.create_post);
                startActivity(new Intent(Main2Activity.this, PostActivity.class));


            }
        });
        GPSTracker gpsTracker = new GPSTracker(this);
        String stringLatitude, stringLongitude;
        String nameOfLocation = "";
        if (gpsTracker.canGetLocation()) {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
            // getting city from latitude, longitude values
            double lat = Double.parseDouble(stringLatitude);
            double longi = Double.parseDouble(stringLongitude);


            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(lat, longi, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String stateName = "lahore";

           // String cityName = addresses.get(0).getAddressLine(0);
            String stateName = addresses.get(0).getAddressLine(1);
           // String countryName = addresses.get(0).getAddressLine(2);
            //  nameOfLocation = ConvertPointToLocation(stringLatitude,stringLongitude);

            TopPostFragment fragment1 =  new TopPostFragment();
            NewPostFragment fragment2 = new NewPostFragment();
            PostActivity fragment3 = new PostActivity();

            //fragment3.setLocationPost(stateName);
            fragment2.setLocationNew(stateName);
            fragment1.setLocationTop(stateName);
/*
            Intent intent = new Intent(Main2Activity.this,PostActivity.class);
            intent.putExtra("location_text",stateName);
            startActivity(intent);
            */
            //Intent.putExtra(“nameofString”,”stringData”)

            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("MID", stateName );
            edit.commit();
            Toast.makeText(this, stateName, Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "location not found", Toast.LENGTH_LONG).show();


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_news) {
            category ="news";

            TopPostFragment fragment =  new TopPostFragment();
            fragment.setName(category);
            NewPostFragment newfragment = new NewPostFragment();
            newfragment.setName1(category);

        } else if (id == R.id.nav_jobs) {
            category ="jobs";

            TopPostFragment fragment =  new TopPostFragment();
            fragment.setName(category);
            NewPostFragment newfragment = new NewPostFragment();
            newfragment.setName1(category);


        } else if (id == R.id.nav_events) {
            category ="event";

            TopPostFragment fragment =  new TopPostFragment();
            fragment.setName(category);
            NewPostFragment newfragment = new NewPostFragment();
            newfragment.setName1(category);


        } else if (id == R.id.nav_traffic) {
            category ="traffic";

            TopPostFragment fragment =  new TopPostFragment();
            fragment.setName(category);
            NewPostFragment newfragment = new NewPostFragment();
            newfragment.setName1(category);






        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "https://evening-cove-67540.herokuapp.com/get_posts.php";
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
           // TextView textView = (TextView) findViewById(R.id.JSONtextview);
            //textView.setText(result);
            JSONSTRING = result;
        }

    }*/

}

