package com.example.agroupkt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.agroupkt.pojos.Products

@Dao
interface ShopDao {
    @get:Query("SELECT * FROM Products ORDER BY name")
    val allProduct: LiveData<List<Products?>?>?

    @Query("DELETE FROM Products")
    fun deleteProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: Products?)
}