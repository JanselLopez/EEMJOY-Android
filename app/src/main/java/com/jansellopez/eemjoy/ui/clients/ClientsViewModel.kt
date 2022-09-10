package com.jansellopez.eemjoy.ui.clients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.domain.GetClientsUseCase
import com.jansellopez.eemjoy.domain.GetPeriodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase,
    private val getPeriodUseCase: GetPeriodUseCase
):ViewModel() {

    val users = MutableLiveData<List<Client>>()
    val period = MutableLiveData<Period>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(city: Int, zone: Int, isNetDisponible: Boolean){
        viewModelScope.launch {
            loading.postValue(true)

            val clientsUseCase = getClientsUseCase(city,zone,isNetDisponible)
            if(!clientsUseCase.isNullOrEmpty())
                users.postValue(clientsUseCase)

            val periodUC = getPeriodUseCase(isNetDisponible)
            if (!clientsUseCase.isNullOrEmpty())
                period.postValue(periodUC)

            loading.postValue(false)
        }
    }
}