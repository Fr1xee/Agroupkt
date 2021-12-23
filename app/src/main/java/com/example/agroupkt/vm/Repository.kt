package com.example.agroupkt.vm

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.agroupkt.pojos.Products
import com.example.agroupkt.db.ShopDao
import com.example.agroupkt.db.RoomDB

class Repository(application: Application) {
    val products: LiveData<List<Products?>?>?
    private val dao: ShopDao? = RoomDB.getDatabase(application.applicationContext)!!.shopDao()

    fun insertProducts(products: Products?) {
        RoomDB.dbWriteExecutor.execute { dao!!.insertProducts(products) }
    }

    fun deleteProducts() {
        dao!!.deleteProducts()
    }

    init {
        products = dao!!.allProduct
    }
}