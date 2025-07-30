package com.example.hilalplaytest.di

import com.example.hilalplaytest.data.local.MenuDao
import com.example.hilalplaytest.data.mapper.MenuMapper
import com.example.hilalplaytest.data.remot.api.ApiService
import com.example.hilalplaytest.data.repos.MenuRepoImpl
import com.example.hilalplaytest.domain.repos.MenuRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        api: ApiService,
        mendDao: MenuDao
    ): MenuRepo = MenuRepoImpl(api, MenuMapper, menuDao = mendDao)

}
