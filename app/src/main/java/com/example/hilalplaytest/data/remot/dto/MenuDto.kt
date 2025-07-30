package com.example.hilalplaytest.data.remot.dto

data class MenuDto(
    val drinks: List<MenuItemDto>,
    val hamburgers: List<MenuItemDto>,
    val pasta: List<MenuItemDto>,
    val sandwiches: List<MenuItemDto>
)