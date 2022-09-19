package com.jansellopez.eemjoy.data.network

import android.content.Context
import android.util.Log
import com.jansellopez.eemjoy.core.HttpsTrustManager
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.toDomain
import com.jansellopez.eemjoy.data.network.modelnetwork.*
import com.jansellopez.eemjoy.data.network.responses.ResponseAddLecturas
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await
import javax.inject.Inject


class UserService @Inject constructor(
    private val apiClient: UserApiClient
){

    suspend fun login(userNetwork: UserNetwork): TokenNetwork = withContext(Dispatchers.IO)
   {
       try {
           HttpsTrustManager.allowAllSSL()
           apiClient.login(userNetwork).await()
       }catch (e:HttpException){
           Log.e("Error",e.toString())
           TokenNetwork("","incorrect_password","")
       }
    }

    suspend fun getCities(token: Token,context: Context):List<CityNetwork> = withContext(Dispatchers.IO) {
        try {
            HttpsTrustManager.allowAllSSL()
            apiClient.getCities("${token.token_type} ${token.access_token}").await().cities
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getCities("${tokenAC.token_type} ${tokenAC.access_token}").await().cities
        }
    }

    suspend fun getZones(token: Token,context: Context):List<ZoneNetwork> = withContext(Dispatchers.IO) {
        try {
            HttpsTrustManager.allowAllSSL()
            apiClient.getZones("${token.token_type} ${token.access_token}").await().zones
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getZones("${tokenAC.token_type} ${tokenAC.access_token}").await().zones
        }
    }

    suspend fun getClients(token: Token,city:Int,zone:Int,context: Context):List<ClientNetwork> = withContext(Dispatchers.IO){
        try {
            HttpsTrustManager.allowAllSSL()
            apiClient.getClients("${token.token_type} ${token.access_token}",city,zone).await().clients
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getClients("${tokenAC.token_type} ${tokenAC.access_token}",city,zone).await().clients
        }
    }

    suspend fun getLecturas(token: Token, zone:Int, context: Context):List<LecturaNetwork> = withContext(Dispatchers.IO){
        try{
            HttpsTrustManager.allowAllSSL()
            apiClient.getLecturas("${token.token_type} ${token.access_token}",zone).await().lecturas
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getLecturas("${tokenAC.token_type} ${tokenAC.access_token}",zone).await().lecturas
        }
    }

    suspend fun getPeriod(token: Token, context: Context):PeriodNetwork = withContext(Dispatchers.IO){
        try{
            HttpsTrustManager.allowAllSSL()
            apiClient.getPeriod("${token.token_type} ${token.access_token}").await().periodo[0]
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getPeriod("${tokenAC.token_type} ${tokenAC.access_token}").await().periodo[0]
        }
    }

    suspend fun pushLecturas(token: Token,lecturaNetworkAdd: LecturaNetworkAdd,context: Context):ResponseAddLecturas = withContext(Dispatchers.IO){
        try{
            Log.w("Lectura ADD" , "${lecturaNetworkAdd.number_cont} ${lecturaNetworkAdd.configuracion_id} " +
                    "${lecturaNetworkAdd.kilovatios} ${lecturaNetworkAdd.lectura_anterior}")

            HttpsTrustManager.allowAllSSL()
            apiClient.addLectura("${token.token_type} ${token.access_token}",lecturaNetworkAdd).await()
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.addLectura("${tokenAC.token_type} ${tokenAC.access_token}",lecturaNetworkAdd).await()
        }
    }

    suspend fun getTarifas(token: Token, context: Context):List<TarifasNetwork> = withContext(Dispatchers.IO){
        try{
            HttpsTrustManager.allowAllSSL()
            apiClient.getTarifas("${token.token_type} ${token.access_token}").await().tarifas
        }catch (e:HttpException){
            Log.e("Error",e.toString())
            val tokenAC = apiClient.login(SharedPreferenceManager.getINstance(context).user.toDomain()).await()
            SharedPreferenceManager.getINstance(context).saveToken(tokenAC.toDomain())
            apiClient.getTarifas("${tokenAC.token_type} ${tokenAC.access_token}").await().tarifas
        }
    }

}
