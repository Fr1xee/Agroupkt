package com.example.agroupkt.rvs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agroupkt.ProductActivity
import com.example.agroupkt.R
import com.example.agroupkt.pojos.Products
import com.squareup.picasso.Picasso

class ProductsRV(private val products: List<Products>) : RecyclerView.Adapter<ProductsRV.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: String = products[position].name + products[position].weight
        holder.productName.text = name
        holder.productPrice.text = products[position].price
        holder.sale.text = products[position].sale
        Picasso.with(ProductActivity.appContext)
            .load(products[position].image)
            .into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView
        var productPrice: TextView
        var sale: TextView
        var productImage: ImageView

        init {
            sale = itemView.findViewById<TextView>(R.id.textView)
            productName = itemView.findViewById<TextView>(R.id.tv_product_name)
            productPrice = itemView.findViewById<TextView>(R.id.tv_product_price)
            productImage = itemView.findViewById(R.id.iv_product)
        }
    }

}