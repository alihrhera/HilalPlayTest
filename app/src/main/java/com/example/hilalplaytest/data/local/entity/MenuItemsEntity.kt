package com.example.hilalplaytest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItemsEntity(
    val available: Boolean,
    val description: String,
    val hot: Boolean,
    @PrimaryKey() var id: Int ,
    val image: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val vegan: Boolean,
    val category: String,
    var isFavorite: Boolean = false
)