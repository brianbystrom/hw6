package com.example.brianbystrom.hw06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetAppsAsync.IData  {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String created_URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
        new GetAppsAsync(MainActivity.this).execute(created_URL);
    }

    public void setupData(final ArrayList<Data> s) {

        for (int i = 0; i < s.size(); i++) {
            Log.d("DEMO", "Title: " + s.get(i).getTitle());
            Log.d("DEMO", "Price: " + s.get(i).getPrice());
            Log.d("Demo", "Image: " + s.get(i).getImage());
        }

        try {
            if (s.size() > 0) {


                //ListView lv = new ListView(MainActivity.this);
                //ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, s);
                lv = (ListView) findViewById(R.id.appListView);
                AppAdapter adapter = new AppAdapter(this, R.layout.app_list_layout, s);
                lv.setAdapter(adapter);

                /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("LISTVIEW", "Game ID: " + s.get(position).getId().toString() + " ID: " + id);
                        Intent toGameDetails = new Intent(MainActivity.this, GameDetails.class);
                        toGameDetails.putExtra("GAME_ID", s.get(position).getId().toString());
                        startActivity(toGameDetails);
                    }
                });*/


                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);




                /*pb.setVisibility(GONE);
                lv.setVisibility(VISIBLE);

                go_btn = (Button) findViewById(R.id.go_btn);
                go_btn.setEnabled(true);

                go_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected > -1) {
                            Intent toGameDetails = new Intent(MainActivity.this, GameDetails.class);
                            Log.d("SELECTED", "SELECTED: " + selected);
                            toGameDetails.putExtra("GAME_ID", String.valueOf(selected));
                            startActivity(toGameDetails);
                        }
                    }
                });*/

            }
        } catch (Exception e) {
            Log.d("Exception", e.toString());
            Toast.makeText(MainActivity.this, "API timed out or results returned no games, please try again.", Toast.LENGTH_SHORT).show();
            //pb.setVisibility(GONE);
        }
    }
}
