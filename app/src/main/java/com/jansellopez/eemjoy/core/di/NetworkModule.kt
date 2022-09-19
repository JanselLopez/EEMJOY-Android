package com.jansellopez.eemjoy.core.di

import com.jansellopez.eemjoy.data.network.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl("http://apix.eemjoyabaj.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit):UserApiClient= retrofit.create(UserApiClient::class.java)
}