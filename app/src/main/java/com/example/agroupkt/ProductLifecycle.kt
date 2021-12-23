package com.example.agroupkt

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import com.example.agroupkt.ProductActivity.Companion.productViewModel
import com.example.agroupkt.pojos.Products
import com.example.agroupkt.rvs.ProductsRV


class ProductLifecycle : LifecycleOwner, LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initViewModel() {
        productViewModel!!.products!!.observe(this, { products ->
                if (ProductActivity.getCount() === products!!.size) {
                    ProductActivity.getProductRV()!!.adapter = ProductsRV(products as List<Products>)
                }
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun productActivityOnPause() {
        ProductActivity.setCount(0)
    }

    override fun getLifecycle(): Lifecycle {
        return ProductActivity.lifecycle!!
    }
}
