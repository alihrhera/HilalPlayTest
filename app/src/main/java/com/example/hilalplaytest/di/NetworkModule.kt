package com.example.hilalplaytest.di

import android.content.Context
import com.example.hilalplaytest.core.NetworkMonitorImpl
import com.example.util.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkChecker(
        @ApplicationContext context: Context
    ): NetworkMonitor = NetworkMonitorImpl(context)
}
