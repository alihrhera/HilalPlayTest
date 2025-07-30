package com.example.hilalplaytest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hilalplaytest.data.local.entity.MenuItemsEntity

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun addRows(items: List<MenuItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun addRow(item: MenuItemsEntity): Long


    @Query("SELECT * FROM menu_items")
    suspend fun getAllRows(): List<MenuItemsEntity>

    @Query("SELECT * FROM menu_items where id=:id")
    suspend fun getItemRow(id: Int): MenuItemsEntity


    @Query("UPDATE menu_items SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteState(id: Int, isFavorite: Boolean): Int
}