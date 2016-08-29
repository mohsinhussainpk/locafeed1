package com.example.mohsinhussain.locafeed;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onRestart() {
        super.onRestart();

        startActivity(new Intent(MainActivity.this, Main2Activity.class));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        loginButton =  (LoginButton) (findViewById(R.id.login_button));
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("VIVZ", "onSuccess");

              //  loginButton.setVisibility(View.INVISIBLE); //<- IMPORTANT
 /*               Intent intent = new Intent(HOME_ACTIVITIES);
                startActivity(intent);
                finish();
*/

                startActivity(new Intent(MainActivity.this, Main2Activity.class));

                // AccessToken accessToken = loginResult.getAccessToken();
               // Profile profile = Profile.getCurrentProfile();
               //Intent I = new Intent(this, Main2Activity.class);
               // startActivity(I);


            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
