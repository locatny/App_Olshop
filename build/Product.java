package com.example.girlnyshop.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int imageResource;
    private int quantity;

    public Product(int imageResource, int quantity) {
        this.imageResource = imageResource;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
