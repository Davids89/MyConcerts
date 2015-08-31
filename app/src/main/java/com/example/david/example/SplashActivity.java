package com.example.david.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.Parse;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "Bg5gl9Q9chUMhr0PIcdQitJJNY5HgQxpFm5KbBp7", "b6EbVE1sCFYAh8lNv9f6TDbLiRGt2kv1SpCh7F3O");

        /*we want to delay a call in the thread so we need to create
        a runnable, which is executed in a handler with a delay of 3000 milsec
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        }, 3000);
    }

    public void nextActivity(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
