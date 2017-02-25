package com.example.brianbystrom.hw06;


import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;



import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetAppsAsync.IData  {

    ListView lv;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    MenuItem refresh, sortInc, sortDec, fav;
    ProgressBar mainPB;
    AppAdapter adapter;
    ArrayList<Data> appList;
    TextView loadingText;


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor clearEditor = sharedpreferences.edit();
        clearEditor.clear();
        clearEditor.commit();


        String created_URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
        new GetAppsAsync(MainActivity.this).execute(created_URL);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh_list:
                Log.d("REFRESH", "CLICKED");
                String created_URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
                new GetAppsAsync(MainActivity.this).execute(created_URL);
                lv = (ListView) findViewById(R.id.appListView);
                lv.setVisibility(GONE);
                mainPB = (ProgressBar) findViewById(R.id.mainPB);
                loadingText = (TextView) findViewById(R.id.loadingText);
                loadingText.setVisibility(VISIBLE);
                mainPB.setVisibility(VISIBLE);
                return true;
            case R.id.favorites:
                Log.d("FAVORITES", "CLICKED");
                Intent toFavorites = new Intent(MainActivity.this, Favorites.class);
                startActivity(toFavorites);
                return true;
            case R.id.sort_inc:
                Collections.sort(appList);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.sort_dec:
                Collections.sort(appList, Collections.<Data>reverseOrder());
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onGroupItemClick(MenuItem item) {
        Log.d("MENU", "MENU ITEM: " + item);
    }

    public void setupData(final ArrayList<Data> s) {

        for (int i = 0; i < s.size(); i++) {
            if(sharedpreferences.contains(s.get(i).getId())) {
                s.get(i).setFavorite(true);
            }
        }

        mainPB = (ProgressBar) findViewById(R.id.mainPB);


        SharedPreferences.Editor editor = sharedpreferences.edit();


            if (s.size() > 0) {

                appList = s;
                loadingText = (TextView) findViewById(R.id.loadingText);
                lv = (ListView) findViewById(R.id.appListView);
                adapter = new AppAdapter(this, R.layout.app_list_layout, appList, MainActivity.this);
                lv.setAdapter(adapter);

                mainPB.setVisibility(GONE);
                loadingText.setVisibility(GONE);
                lv.setVisibility(VISIBLE);

            } else {
                Toast.makeText(MainActivity.this, "No apps were found please refresh using the menu in the top right.", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}
