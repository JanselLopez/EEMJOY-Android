package com.jansellopez.eemjoy.ui.clients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.domain.GetClientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase
):ViewModel() {

    val users = MutableLiveData<List<Client>>()

    fun onCreate(city:String,zone:String){
        viewModelScope.launch {
            val clientsUseCase = getClientsUseCase(city,zone)
            if(!clientsUseCase.isNullOrEmpty())
                users.postValue(clientsUseCase)
        }
    }
}