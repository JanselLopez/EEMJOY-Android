package com.jansellopez.eemjoy.core.di

import com.jansellopez.eemjoy.data.network.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl("https://apix.eemjoyabaj.com/public/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit):UserApiClient= retrofit.create(UserApiClient::class.java)
}