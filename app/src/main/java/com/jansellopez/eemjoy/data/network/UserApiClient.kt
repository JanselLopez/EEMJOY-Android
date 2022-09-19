package com.jansellopez.eemjoy.data.network

import com.jansellopez.eemjoy.data.network.modelnetwork.LecturaNetworkAdd
import com.jansellopez.eemjoy.data.network.modelnetwork.TokenNetwork
import com.jansellopez.eemjoy.data.network.modelnetwork.UserNetwork
import com.jansellopez.eemjoy.data.network.responses.*
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

    @GET("clientes/{city}/{zone}")
    fun getClients(@Header("Authorization") auth:String,
                   @Path("city") city:Int,
                   @Path("zone") zone:Int):Call<ResponseClient>

    @GET("lecturas/{zone}")
    fun getLecturas(@Header("Authorization") auth:String,
                    @Path("zone") zone:Int):Call<ResponseLectura>

    @GET("periodo")
    fun getPeriod(@Header("Authorization") auth:String):Call<ResponsePeriod>

    @POST("lectura")
    fun addLectura(@Header("Authorization") auth:String,
                   @Body lecturaNetworkAdd: LecturaNetworkAdd
                   ):Call<ResponseAddLecturas>

    @GET("tarifas")
    fun getTarifas(@Header("Authorization") auth:String):Call<ResponseTarifas>

}