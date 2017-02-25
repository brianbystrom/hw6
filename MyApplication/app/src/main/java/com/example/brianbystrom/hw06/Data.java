/*
Assignment #: In Class 04
File Name: Data.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw06;

import java.util.Comparator;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data implements Comparable<Data> {
    String title, image, id;
    Double price;
    Boolean favorite;

    public int compareTo(Data d)
    {
        return getPrice().compareTo(d.getPrice());
    }



    static class AgeComparator implements Comparator<Data>
    {
        public int compare(Data d1, Data d2)
        {
            Double app1 = d1.getPrice();
            Double app2 = d2.getPrice();

            if (app1 == app2)
                return 0;
            else if (app1 > app2)
                return 1;
            else
                return -1;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    public Data() {

    }
}
