/*
Assignment #: In Class 04
File Name: Data.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw06;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data {
    String title;
    Double price;

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
