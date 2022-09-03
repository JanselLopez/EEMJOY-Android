package com.jansellopez.eemjoy.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://apix.eemjoyabaj.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}