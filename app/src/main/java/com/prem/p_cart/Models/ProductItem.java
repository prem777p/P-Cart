package com.prem.p_cart.Models;

import com.hishd.tinycart.model.Item;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductItem implements Item, Serializable {
   private String name, icon, price, description;
   private final int id;
   private double  rating;
   private int quantity, priceInt;


    public ProductItem(String name, String icon, double rating, int price, int id, String description) {
        this.name = name;
        this.icon = icon;
        this.rating = rating;
        this.price = "From ₹ "+price;
        priceInt = price;
        this.id = id;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public int getId() { return id; }
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getItemPrice() {
        return new BigDecimal(priceInt);
    }

    @Override
    public String getItemName() {
        return name;
    }

}
