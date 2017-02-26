/*
Assignment #: HW 06
File Name: Favorites.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw06;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Favorites extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    ListView lv;
    FavoriteAdapter adapter;
    ArrayList<Data> favoritesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesList = new ArrayList<Data>();


        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);


        Map<String, ?> allEntries = sharedpreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            Data favorite = new Data();
            String app = entry.getValue().toString();
            String appSplit[] = app.split("\\|");


            favorite.setId(appSplit[0]);
            favorite.setTitle(appSplit[1]);
            favorite.setPrice(Double.parseDouble(appSplit[2]));
            favorite.setImage(appSplit[3]);


            favorite.setFavorite(true);

            favoritesList.add(favorite);
        }

        if (favoritesList.size() > 0) {

            lv = (ListView) findViewById(R.id.favoritesListView);
            adapter = new FavoriteAdapter(this, R.layout.app_list_layout, favoritesList, Favorites.this);
            lv.setAdapter(adapter);


        } else {
            Toast.makeText(Favorites.this, "You have no favorites selected.  Please go back and select some favorites.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}
