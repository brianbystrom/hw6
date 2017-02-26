/*
Assignment #: HW 06
File Name: FavoriteAdapter.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw06;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by brianbystrom on 2/23/17.
 */

public class FavoriteAdapter extends ArrayAdapter<Data> {

    private final List<Data> mData;;
    Context mContext;
    int mResource;
    int selectedPosition = -1;
    RadioGroup rg;
    Activity mActivity;
    TextView tv;
    int year;
    ImageView game_image;
    ImageButton favorite_image;
    SharedPreferences sharedpreferences;




    public FavoriteAdapter(Context context, int resource, List<Data> objects, Activity parentActivity) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
        this.mActivity = parentActivity;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageButton favorite_image;
        View rowView= convertView;

        sharedpreferences = mContext.getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);



        if(rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(mResource, parent, false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.appInfo = (TextView) rowView.findViewById(R.id.appInfo);
            viewHolder.appImage = (ImageView) rowView.findViewById(R.id.appImage);
            viewHolder.favoriteImage = (ImageButton) rowView.findViewById(R.id.favoriteImage);

            rowView.setTag(viewHolder);


        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        final Data data = mData.get(position);
        viewHolder.appInfo.setText(data.getTitle() + "\nPrice: $" + data.getPrice().toString());
        Picasso.with(mContext).load(data.getImage()).into(viewHolder.appImage);


        if(sharedpreferences.contains(data.getId())) viewHolder.favoriteImage.setImageResource(R.mipmap.favorite);
        else viewHolder.favoriteImage.setImageResource(R.mipmap.unfavorite);

        viewHolder.favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data.getFavorite()) {

                    new AlertDialog.Builder(mActivity)
                            .setTitle("Remove favorite?")
                            .setMessage("Do you really want to remove favorite?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    data.setFavorite(false);
                                    Log.d("NAME", "TITLE: " + data.getTitle());
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.remove(data.getId());
                                    editor.commit();
                                    mData.remove(data);
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                } else {

                }
            }
        });

        return rowView;
    }

}

