package com.jansellopez.eemjoy.data.network

import com.jansellopez.eemjoy.core.HttpsTrustManager
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.network.modelnetwork.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject


class UserService @Inject constructor(
    private val apiClient: UserApiClient
){

    private lateinit var cities:List<CityNetwork>

    suspend fun login(userNetwork: UserNetwork): TokenNetwork = withContext(Dispatchers.IO)
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

    suspend fun getClients(token: Token,city:Int,zone:Int):List<ClientNetwork> = withContext(Dispatchers.IO){
        HttpsTrustManager.allowAllSSL()
        apiClient.getClients("${token.token_type} ${token.access_token}",city,zone).await().clients
    }

    suspend fun getLecturas(token: Token, zone:Int):List<LecturaNetwork> = withContext(Dispatchers.IO){
        HttpsTrustManager.allowAllSSL()
        apiClient.getLecturas("${token.token_type} ${token.access_token}",zone).await().lecturas
    }

    suspend fun getPeriod(token: Token):PeriodNetwork = withContext(Dispatchers.IO){
        HttpsTrustManager.allowAllSSL()
        apiClient.getPeriod("${token.token_type} ${token.access_token}").await().periodo[0]
    }

}
