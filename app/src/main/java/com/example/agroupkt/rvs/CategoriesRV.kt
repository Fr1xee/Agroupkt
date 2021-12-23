package com.example.agroupkt.rvs

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.agroupkt.ProductActivity
import com.example.agroupkt.R

class CategoriesRV(private val categories: List<String>) :
    RecyclerView.Adapter<CategoriesRV.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryButton.text = categories[position]
        holder.categoryButton.setOnClickListener { view: View ->
            val context = view.context
            val startIntent = Intent(context, ProductActivity::class.java)
            startIntent.putExtra("category", categories[position])
            Log.i("CATory", categories[position])
            context.startActivity(startIntent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryButton: Button = itemView.findViewById(R.id.but_category)
    }
}