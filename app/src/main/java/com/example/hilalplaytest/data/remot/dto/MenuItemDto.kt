package com.example.hilalplaytest.data.remot.dto

data class MenuItemDto(
    val available: Boolean,
    val description: String,
    val hot: Boolean,
    val id: Int,
    val image: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val vegan: Boolean
)

