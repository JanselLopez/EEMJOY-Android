package com.jansellopez.eemjoy.data.network

import android.util.Log
import com.jansellopez.eemjoy.core.HttpsTrustManager
import com.jansellopez.eemjoy.data.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import javax.inject.Inject


class UserService @Inject constructor(
    private val apiClient: UserApiClient
){

    private lateinit var cities:List<CityNetwork>
   /* suspend fun getCategories(): List<String> {
        Log.e("entro","service")
       return withContext(Dispatchers.IO) {
            apiClient.getCategories().body() ?: emptyList()
        }
    }

    suspend fun getJokeByCategory(category: String):JokeNetwork=
        withContext(Dispatchers.IO){
        apiClient.getJokeByCategory("random?category=$category").body()?: JokeNetwork("","")
    }*/
    suspend fun login(userNetwork: UserNetwork):TokenNetwork = withContext(Dispatchers.IO)
   {
            HttpsTrustManager.allowAllSSL()
            apiClient.login(userNetwork).await()
    }

    suspend fun getCities(token: Token):List<CityNetwork> = withContext(Dispatchers.IO) {
        HttpsTrustManager.allowAllSSL()
        apiClient.getCities("${token.token_type} ${token.access_token}").await().cities
        }

    suspend fun getZones(token: Token):List<ZoneNetwork> = withContext(Dispatchers.IO) {
        HttpsTrustManager.allowAllSSL()
        apiClient.getZones("${token.token_type} ${token.access_token}").await().zones
    }

    suspend fun getClients(token: Token):List<ClientNetwork> = withContext(Dispatchers.IO){
        HttpsTrustManager.allowAllSSL()
        apiClient.getClients("${token.token_type} ${token.access_token}").await().clients
    }






}
