package com.jansellopez.eemjoy.data.network

import retrofit2.Call
import retrofit2.http.*

interface UserApiClient {

    @POST("login")
    fun login(
        @Body userNetwork: UserNetwork
    ):Call<TokenNetwork>

    @GET("municipios")
    fun getCities(@Header("Authorization") auth:String):Call<ResponseCity>

    @GET("direcciones")
    fun getZones(@Header("Authorization") auth:String):Call<ResponseZone>

}