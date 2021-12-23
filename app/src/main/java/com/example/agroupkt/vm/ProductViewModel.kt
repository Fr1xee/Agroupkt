package com.example.agroupkt.vm


import android.app.Application
import androidx.lifecycle.LiveData
import com.example.agroupkt.pojos.Products
import androidx.lifecycle.AndroidViewModel

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository(application)

    val products: LiveData<List<Products?>?>? = repository.products

    fun insertProducts(products: Products?) {
        repository.insertProducts(products)
    }

    fun deleteProducts() {
        repository.deleteProducts()
    }


}