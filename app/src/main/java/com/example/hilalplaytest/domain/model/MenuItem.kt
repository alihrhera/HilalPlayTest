package com.example.hilalplaytest.domain.model

data class MenuItem(
    val isAvailable: Boolean,
    val description: String,
    val hot: Boolean,
    val id: Int,
    val image: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val vegan: Boolean,
    var isFavorite: Boolean=false
)


