package com.example.hilalplaytest

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.work.Configuration
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
class HilalApp: Application() , ViewModelStoreOwner, Configuration.Provider {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltWorkerFactoryEntryPoint {
        fun workerFactory(): HiltWorkerFactory
    }


    override val viewModelStore: ViewModelStore
        get() = appViewModelStore


    companion object {
        lateinit var appContext: Application
        private val appViewModelStore: ViewModelStore by lazy {
            ViewModelStore()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(EntryPoints.get(this, HiltWorkerFactoryEntryPoint::class.java).workerFactory())
            .setMinimumLoggingLevel(Log.DEBUG).build()


}