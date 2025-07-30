package ali.hrhera.plantsinformations.data.network.di

import com.example.hilalplaytest.data.remot.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProviderModule {


    @Provides
    @Singleton
    fun provideApiService(@Named("normalRetrofit")retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



}
