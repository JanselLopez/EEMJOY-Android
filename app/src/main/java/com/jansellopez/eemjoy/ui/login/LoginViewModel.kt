package com.jansellopez.eemjoy.ui.login

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.core.HttpsTrustManager
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.data.model.toDomain
import com.jansellopez.eemjoy.domain.PostLoginUseCase
import cu.jansellopez.custom_snackbars.CustomSnackBar
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
    val loading = MutableLiveData<Boolean>()

    fun login(userL: User,activity: Activity, coordinatorLayout: CoordinatorLayout, isConnect:Boolean){

        viewModelScope.launch {

            loading.postValue(true)

            user.postValue(userL)
            val tokenL = postLoginUseCase(userL, isConnect)

            if (tokenL.access_token.isNotEmpty())
                token.postValue(tokenL)

            if (tokenL.token_type == "incorrect_password")
                CustomSnackBar(activity,coordinatorLayout).showError(activity.resources.getString(R.string.incorrect_password))
            else if (tokenL.token_type == "no_connection")
                CustomSnackBar(activity,coordinatorLayout).showError(activity.resources.getString(R.string.no_connection))

            Log.e("tokenVM", tokenL.access_token)

            loading.postValue(false)
        }

    }

}