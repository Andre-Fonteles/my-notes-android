package dev.fonteles.mynotes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fonteles.mynotes.data.datasource.ILoginApi
import dev.fonteles.mynotes.data.datasource.ServerConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideBuilder(): Retrofit {
        return Retrofit.Builder().baseUrl(ServerConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): ILoginApi {
        return retrofit.create(ILoginApi::class.java)
    }
}