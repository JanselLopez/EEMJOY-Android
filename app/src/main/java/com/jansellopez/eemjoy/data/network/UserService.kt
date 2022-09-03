package com.jansellopez.eemjoy.data.network

import android.util.Log
import com.jansellopez.eemjoy.data.network.TokenNetwork
import com.jansellopez.eemjoy.data.network.UserApiClient
import com.jansellopez.eemjoy.data.network.UserNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(
    private val apiClient: UserApiClient
){
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
    suspend fun login(userNetwork: UserNetwork):TokenNetwork{
       return withContext(Dispatchers.IO){
            apiClient.login(userNetwork).body()?:TokenNetwork("","")
        }
    }

}
