package com.jansellopez.eemjoy.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    fun getRetrofit():Retrofit{
        val client = OkHttpClient.Builder()
            .connectTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl("http://apix.eemjoyabaj.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}