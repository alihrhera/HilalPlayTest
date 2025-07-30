package com.example.hilalplaytest.data.mapper

import com.example.hilalplaytest.data.local.entity.MenuItemsEntity
import com.example.hilalplaytest.data.remot.dto.MenuDto
import com.example.hilalplaytest.domain.model.MenuTap

object MenuMapper {


    fun toDomain(menuDto: MenuDto) = listOf(
        MenuTap(
            name = "Drinks",
            items = menuDto.drinks.map { it.toDomain() },
        ),
        MenuTap(
            name = "Hamburgers",
            items = menuDto.hamburgers.map { it.toDomain() },
        ),
        MenuTap(
            name = "Pasta",
            items = menuDto.pasta.map { it.toDomain() },
        ),
        MenuTap(
            name = "Sandwiches",
            items = menuDto.sandwiches.map { it.toDomain() },
        ),
    )

    suspend fun toDomain(items: List<MenuItemsEntity>): List<MenuTap> {
        return items.groupBy { it.category }.map { (category, groupItems) ->
                MenuTap(
                    name = category, items = groupItems.map { it.toDomain() })
            }
    }

    fun MenuDto.toEntities(): List<MenuItemsEntity> {
        return listOf(
            drinks.map { it.toEntity("Drinks") },
            hamburgers.map { it.toEntity("Hamburgers") },
            pasta.map { it.toEntity("Pasta") },
            sandwiches.map { it.toEntity("Sandwiches") }).flatten()
    }
}
