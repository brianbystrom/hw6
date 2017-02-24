package com.example.brianbystrom.hw06;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by brianbystrom on 2/23/17.
 */

public class AppAdapter extends ArrayAdapter<Data> {

    List<Data> mData;
    Context mContext;
    int mResource;
    int selectedPosition = -1;
    RadioGroup rg;
    TextView tv;
    int year;
    ImageView game_image;

    public AppAdapter(Context context, int resource, List<Data> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            //rg = (RadioGroup) convertView.findViewById(R.id.game_list_radio_group);
            tv = (TextView) convertView.findViewById(R.id.appInfo);
            Data data = mData.get(position);
            //game_image = (ImageView) convertView.findViewById(R.id.appImage);

            //String created_URL = "http://thegamesdb.net/api/GetGame.php?id=" + data.getId();
            //new GetGameInfoAsync(GameAdapter.this).execute(created_URL);

            //new SetImageAsync(AppAdapter.this).execute("http://thegamesdb.net/banners/clearlogo/" + data.getId() + ".png");





//and so on

            tv.setText(data.getTitle() + "\nPrice: $" + data.getPrice().toString());

            //RadioButton rb = (RadioButton) convertView.findViewById(R.id.game_radio_button);
            //rb.setText(data.getTitle());
            //rg.addView(rb);

        }




        return convertView;
    }

    /*public void setupData(final ArrayList<Data> s) {


        try {
            if (s.size() > 0) {

                if (s.get(0).getLogo() != null) {
                    Log.d("IMAGE", s.get(0).getLogo().toString());

                }

            }

        } catch (Exception e) {
            //Toast.makeText(GameAdapter.this, "API timed out or could not find game, please try again.", Toast.LENGTH_SHORT).show();

        }

    }

    public void setupImage(Bitmap bitmap) {

        //ImageView game_image;
        //game_image = (ImageView) convertView.findViewById(R.id.imageView);
        game_image.setImageBitmap(bitmap);


    }*/

}

