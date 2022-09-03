package com.jansellopez.eemjoy.data.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface UserApiClient {
   /* @GET("categories")
    suspend fun getCategories():Response<List<String>>
    @GET
    suspend fun getJokeByCategory(@Url url:String):Response<UserNetwork>*/

    @HTTP(method = "GET", path = "login", hasBody = true)
    fun login(@Body userNetwork: UserNetwork):Response<TokenNetwork>

}