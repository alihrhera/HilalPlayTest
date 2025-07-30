package com.example.hilalplaytest.data.mapper

import com.example.hilalplaytest.data.local.entity.MenuItemsEntity
import com.example.hilalplaytest.data.remot.dto.MenuItemDto
import com.example.hilalplaytest.domain.model.MenuItem

fun MenuItemDto.toDomain() = MenuItem(
    id = id,
    name = name,
    price = price,
    description = description,
    vegan = vegan,
    hot = hot,
    rating = rating,
    image = image,
    isAvailable = available
)


fun MenuItemsEntity.toDomain() = MenuItem(
    id = id,
    name = name,
    price = price,
    description = description,
    vegan = vegan,
    hot = hot,
    rating = rating,
    image = image,
    isAvailable = available,
    isFavorite = isFavorite
)



fun MenuItemDto.toEntity(category: String): MenuItemsEntity {
    return MenuItemsEntity(
        id = id,
        name = name,
        price = price,
        description = description,
        vegan = vegan,
        hot = hot,
        rating = rating,
        image = image,
        available = available,
        category = category
    )
}
