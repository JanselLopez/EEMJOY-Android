package com.jansellopez.eemjoy.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.domain.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
):ViewModel() {

    val user = MutableLiveData<User>()
    val token = MutableLiveData<Token>()

    fun login(userL: User){
        user.postValue(userL)
        viewModelScope.launch {
           val tokenL =  postLoginUseCase(userL)
            if(!tokenL.access_token.isNullOrEmpty())
               token.postValue(tokenL)
        }
    }
}