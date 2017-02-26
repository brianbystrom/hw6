/*
Assignment #: HW 06
File Name: SetImageAsync.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class SetImageAsync extends AsyncTask<String, Void, Bitmap> {

    IData activity;

    public SetImageAsync(IData activity) {
        this.activity = activity;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);



    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.setupImage(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream in = null;
        Bitmap image = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            in = con.getInputStream();

            image = BitmapFactory.decodeStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public interface IData {
        public void setupImage(Bitmap bitmap);
    }
}
