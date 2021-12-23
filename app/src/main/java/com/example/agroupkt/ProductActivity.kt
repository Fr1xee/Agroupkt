package com.example.agroupkt

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agroupkt.MainActivity.Companion.ref
import com.example.agroupkt.pojos.Products
import com.example.agroupkt.vm.ProductViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productRV = findViewById(R.id.rv_product)
        productRV!!.layoutManager = GridLayoutManager(this, 2)
        productRV!!.addItemDecoration(SpacesItemDecoration(25))
        Companion.lifecycle = lifecycle
        val productLifecycle = ProductLifecycle()
        Companion.lifecycle!!.addObserver(productLifecycle)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        appContext = applicationContext
        dataFromDB
    }

    private val dataFromDB: Unit
        get() {
            val vListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var snapshot = snapshot
                    productViewModel!!.deleteProducts()
                    Log.i("CATory", "onDataChange")
                    val intent = intent
                    var path = ""
                    when (intent.getStringExtra("category")) {
                        "Алкоголь" -> path = "alcohol"
                        "Консервы" -> path = "canned"
                        "Мучные изделия" -> path = "flour_products"
                        "Подар-ые сертификаты" -> path = "gift_certificates"
                        "Бакалея" -> path = "grocery_stores"
                        "Хоз. товары" -> path = "household"
                        "Продукты" -> path = "products"
                        "Сладости" -> path = "sweets"
                        "Табак" -> path = "tobacco"
                    }
                    snapshot = snapshot.child("/$path")
                    for (ds in snapshot.children) {
                        val products = Products()
                        products.setCategory(intent.getStringExtra("category"))
                        products.setName(ds.child("name").value.toString())
                        products.setPrice(ds.child("price").value.toString())
                        products.setWeight(ds.child("weight").value.toString())
                        products.setImage(ds.child("image").value.toString())
                        products.setSale(ds.child("sale").value.toString())
                        productViewModel!!.insertProducts(products)
                        count++
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            }
            ref!!.addValueEventListener(vListener)
        }

    companion object {
        private var productRV: RecyclerView? = null
        var productViewModel: ProductViewModel? = null
        var lifecycle: Lifecycle? = null
        var appContext: Context? = null
            private set
        private var count = 0

        fun getProductRV(): RecyclerView? {
            return productRV
        }

        fun getCount(): Int {
            return count
        }

        fun setCount(count: Int) {
            Companion.count = count
        }
    }
}