package com.example.girlnyshop.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * FROM product ORDER BY id ASC")
    List<Product> getAllProducts();
}
