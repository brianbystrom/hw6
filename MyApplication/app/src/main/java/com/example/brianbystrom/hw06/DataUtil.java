package com.example.brianbystrom.hw06;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brianbystrom on 2/6/17.
 */

//String title, author, published_date, description, urlToImage;


public class DataUtil {

    static public class DataJSONParser {
        static ArrayList<Data> parseData(String in) {
            ArrayList<Data> dataList = new ArrayList();

            try {
                JSONObject root = new JSONObject(in);
                JSONObject dataJSONObject = root.getJSONObject("feed");

                JSONArray entryArray = dataJSONObject.getJSONArray("entry");


                for (int i = 0; i<entryArray.length(); i++) {
                    //JSONObject dataJSONObject2 = dataJSONObject.getJSONObject("entry");

                    if (entryArray.getJSONObject(i) != null) {

                        Data data = new Data();

                        JSONObject nameObject = entryArray.getJSONObject(i);
                        JSONObject nameObject2 = nameObject.getJSONObject("im:name");
                        data.setTitle(nameObject2.getString("label"));

                        JSONObject imgObject = entryArray.getJSONObject(i);
                        JSONArray imgArray2 = imgObject.getJSONArray("im:image");
                        JSONObject imgObject2 = imgArray2.getJSONObject(0);
                        data.setImage(imgObject2.getString("label"));

                        JSONObject idObject = entryArray.getJSONObject(i);
                        JSONObject idObject2 = idObject.getJSONObject("id");
                        JSONObject idObject3 = idObject2.getJSONObject("attributes");
                        data.setId(idObject3.getString("im:id"));

                        JSONObject priceObject = entryArray.getJSONObject(i);
                        JSONObject priceObject2 = priceObject.getJSONObject("im:price");
                        JSONObject priceAttributes = priceObject2.getJSONObject("attributes");

                        data.setPrice(Double.parseDouble(priceAttributes.getString("amount")));

                        data.setFavorite(false);

                        dataList.add(data);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return dataList;
        }
    }
}
