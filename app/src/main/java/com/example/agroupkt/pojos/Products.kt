package com.example.agroupkt.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
class Products {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var weight: String? = null
    var price: String? = null
    var category: String? = null
    var image: String? = null
    var sale: String? = null

    @JvmName("getSale1")
    fun getSale(): String? {
        return sale
    }

    @JvmName("setSale1")
    fun setSale(igor: String) {
        sale = igor
    }

    @JvmName("getId1")
    fun getId(): Int {
        return id
    }

    @JvmName("setId1")
    fun setId(id: Int) {
        this.id = id
    }

    @JvmName("getName1")
    fun getName(): String? {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name
    }

    @JvmName("getWeight1")
    fun getWeight(): String? {
        return weight
    }

    @JvmName("setWeight1")
    fun setWeight(weight: String?) {
        this.weight = weight
    }

    @JvmName("getPrice1")
    fun getPrice(): String? {
        return price
    }

    @JvmName("setPrice1")
    fun setPrice(price: String?) {
        this.price = price
    }

    @JvmName("getCategory1")
    fun getCategory(): String? {
        return category
    }

    @JvmName("setCategory1")
    fun setCategory(category: String?) {
        this.category = category
    }

    @JvmName("getImage1")
    fun getImage(): String? {
        return image
    }

    @JvmName("setImage1")
    fun setImage(image: String?) {
        this.image = image
    }
}