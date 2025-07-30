package com.example.hilalplaytest.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilalplaytest.data.local.entity.MenuItemsEntity

@Database(
    entities = [MenuItemsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun menuDAO(): MenuDao
}
