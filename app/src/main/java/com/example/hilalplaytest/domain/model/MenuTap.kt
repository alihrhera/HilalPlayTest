package com.example.hilalplaytest.domain.model

data class MenuTap (
    val name: String,
    val items:List<MenuItem>
)

typealias Menu = List<MenuTap>
