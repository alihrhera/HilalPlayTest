package com.example.hilalplaytest.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.hilalplaytest.data.local.LocalDataBase
import com.example.hilalplaytest.data.local.MenuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalDataBase {
        return Room.databaseBuilder(
            context,
            LocalDataBase::class.java,
            "menu_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuDao(database: LocalDataBase): MenuDao {
        return database.menuDAO()
    }
}