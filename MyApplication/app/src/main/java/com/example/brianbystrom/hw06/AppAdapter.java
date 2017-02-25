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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by brianbystrom on 2/23/17.
 */

public class AppAdapter extends ArrayAdapter<Data> implements SetImageAsync.IData {

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




    public AppAdapter(Context context, int resource, List<Data> objects, Activity parentActivity) {
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

        if(sharedpreferences.contains(data.getId())) viewHolder.favoriteImage.setImageResource(R.mipmap.favorite);
        else viewHolder.favoriteImage.setImageResource(R.mipmap.unfavorite);

        Log.d("SP", "TITLE: " + data.getTitle() + " SP: " + sharedpreferences.contains(data.getId()));

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
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                } else {
                    new AlertDialog.Builder(mActivity)
                            .setTitle("Add favorite?")
                            .setMessage("Do you really want to add favorite?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    data.setFavorite(true);
                                    Log.d("NAME", "TITLE: " + data.getTitle());

                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    Set<String> app = new HashSet<String>();
                                    app.add(data.getId());
                                    app.add(data.getTitle());
                                    app.add(data.getPrice().toString());
                                    app.add(data.getImage());
                                    editor.putStringSet(data.getId(), app);
                                    editor.commit();
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }


            }
        });


        //rg = (RadioGroup) convertView.findViewById(R.id.game_list_radio_group);
            //tv = (TextView) rowLayout.findViewById(R.id.appInfo);
           // Log.d("Adapter", data.getTitle());
           // Log.d("Adapter", "SIZE " + position);
            //favorite_image = (ImageButton) rowLayout.findViewById(R.id.favoriteImage);
            //favorite_image.setOnClickListener(checkFavoriteHandler);
            //game_image = (ImageView) rowLayout.findViewById(R.id.appImage);
            //viewHolder.favoriteImage = (ImageButton) rowLayout.findViewById(R.id.favoriteImage);
            //viewHolder.favoriteImage.setId(Integer.parseInt(data.getId()));
            //Log.d("Adapter", "ID: " + favorite_image.getId());


            //String created_URL = data.getImage();
            //new GetGameInfoAsync(GameAdapter.this).execute(created_URL);

            //new SetImageAsync(AppAdapter.this).execute(data.getImage());

            //tv.setId(Integer.parseInt(data.getId()));
            //RadioButton rb = (RadioButton) convertView.findViewById(R.id.game_radio_button);
            //rb.setText(data.getTitle());
            //rg.addView(rb);






        return rowView;
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

    }*/

    public void setupImage(Bitmap bitmap) {

        //ImageView game_image;
        //game_image = (ImageView) convertView.findViewById(R.id.imageView);
        game_image.setImageBitmap(bitmap);


    }

    public void changeFavorite(View v) {
    }

}

