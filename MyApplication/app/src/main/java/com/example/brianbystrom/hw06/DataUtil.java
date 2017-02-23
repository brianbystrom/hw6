package com.example.brianbystrom.hw06;

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
                    JSONArray appArray = dataJSONObject.getJSONArray("entry");

                    Data data = new Data();

                    //data.setTitle(dataJSONObject2.getString("im:name"));
                    //JSONArray nameArray = appArray.getJSONArray(i);
                    JSONObject nameObject = entryArray.getJSONObject(i);
                    JSONObject nameObject2 = nameObject.getJSONObject("im:name");
                    data.setTitle(nameObject2.getString("label"));
                    //Log.d("DEMO",entryArray.getString(0));
                    //Log.d("NAME",nameObject2.getString("label"));

                    JSONObject priceObject = entryArray.getJSONObject(i);
                    JSONObject priceObject2 = priceObject.getJSONObject("im:price");
                    JSONObject priceAttributes = priceObject2.getJSONObject("attributes");
                    //data.setTitle(nameObject.getString("label"));
                    //Log.d("DEMO",entryArray.getString(0));
                    //Log.d("PRICE",priceAttributes.getString("amount"));
                    data.setPrice(Double.parseDouble(priceAttributes.getString("amount")));

                    dataList.add(data);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return dataList;
        }
    }
}
