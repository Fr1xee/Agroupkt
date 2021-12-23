package com.example.agroupkt

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agroupkt.rvs.CategoriesRV
import com.google.firebase.database.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    var categoriesRV: RecyclerView? = null
    var start = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        categoriesRV = findViewById(R.id.rv_categories)
        categoriesRV!!.layoutManager = GridLayoutManager(this, 3)
        mDatabase = FirebaseDatabase.getInstance()
        ref = mDatabase!!.getReference("categories")
        dataFromDB
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint)
        when (id) {
            R.id.white -> {
                constraintLayout.setBackgroundColor(Color.WHITE)
                return true
            }
            R.id.black -> {
                constraintLayout.setBackgroundColor(Color.GRAY)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val dataFromDB: Unit
        get() {
            val vListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    categories = ArrayList()
                    for (ds in snapshot.children) {
                        when (ds.key) {
                            "alcohol" -> categories!!.add("Алкоголь")
                            "canned" -> categories!!.add("Консервы")
                            "flour_products" -> categories!!.add("Мучные изделия")
                            "gift_certificates" -> categories!!.add("Подар-ые сертификаты")
                            "grocery_stores" -> categories!!.add("Бакалея")
                            "household" -> categories!!.add("Хоз. товары")
                            "products" -> categories!!.add("Продукты")
                            "sweets" -> categories!!.add("Сладости")
                            "tobacco" -> categories!!.add("Табак")
                            "rostislav" -> categories!!.add("РОСТ")
                        }
                    }
                    categoriesRV!!.adapter = CategoriesRV(categories as ArrayList<String>)
                    start = true
                }

                override fun onCancelled(error: DatabaseError) {}
            }
            ref!!.addValueEventListener(vListener)
        }

    companion object {
        var mDatabase: FirebaseDatabase? = null
        var ref: DatabaseReference? = null
        var categories: MutableList<String>? = null
    }
}