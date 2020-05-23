package com.test.firebaseevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.test.firebaseevents.db.PreferenceHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(PreferenceHelper.getInstance(SplashActivity.this).getString(PreferenceHelper.Key.EMAIL)!=null){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }, 1000);


    }
}
