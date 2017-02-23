package com.example.brianbystrom.hw06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAppsAsync.IData  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String created_URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
        new GetAppsAsync(MainActivity.this).execute(created_URL);
    }

    public void setupData(final ArrayList<Data> s) {

        for (int i = 0; i < s.size(); i++) {
            Log.d("DEMO", "Data gotten" + s.get(i).getTitle());

        }
    }
}
