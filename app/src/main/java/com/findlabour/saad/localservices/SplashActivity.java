package com.findlabour.saad.localservices;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.findlabour.saad.localservices.Commom.Common;

import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/small.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_splash);

        if (Common.isConnectedToInternet(getBaseContext())) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }/*else{
            Intent intent = new Intent(getApplicationContext(),Retry.class);
            startActivity(intent);
            finish();
        }*/
    }

}
