package com.jansellopez.eemjoy.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.core.HttpsTrustManager
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.data.model.toDomain
import com.jansellopez.eemjoy.data.network.TokenNetwork
import com.jansellopez.eemjoy.data.network.UserApiClient
import com.jansellopez.eemjoy.data.network.toDomain
import com.jansellopez.eemjoy.domain.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
):ViewModel() {

    val user = MutableLiveData<User>()
    val token = MutableLiveData<Token>()

    fun login(userL: User) {
        viewModelScope.launch {
            user.postValue(userL)
            val tokenL = postLoginUseCase(userL)
            if (!tokenL.access_token.isNullOrEmpty())
                token.postValue(tokenL)
            Log.e("tokenVM", tokenL.access_token)
        }
    }
}