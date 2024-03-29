package com.jansellopez.eemjoy.ui.clients

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.data.model.Tarifa
import com.jansellopez.eemjoy.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase,
    private val getPeriodUseCase: GetPeriodUseCase,
    private val pushAllsLecturasUseCase: PushAllsLecturasUseCase,
    private val getLecturasUseCase: GetLecturasUseCase,
    private val getTarifasUseCase: GetTarifasUseCase,
    private val getClientByNameOrCountUseCase: GetClientByNameOrCountUseCase,
    private val getLastLecturaFromDatabaseUseCase: GetLastLecturaFromDatabaseUseCase
):ViewModel() {

    val users = MutableLiveData<List<Client>>()
    val period = MutableLiveData<Period>()
    val loading = MutableLiveData<Boolean>()
    val lecturas = MutableLiveData<List<Lectura>>()
    val tarifas = MutableLiveData<List<Tarifa>>()
    val lastsLecturas = MutableLiveData<List<Lectura>>()

    fun onCreate(city: Int, zone: Int, isNetDisponible: Boolean,context: Context){
        viewModelScope.launch {
            loading.postValue(true)

            getClientsUseCase(city,zone,isNetDisponible,context)
            val clientsUseCase = getClientByNameOrCountUseCase("",false)
            if (!clientsUseCase.isNullOrEmpty())
            users.postValue(clientsUseCase)


            val periodUC = getPeriodUseCase(isNetDisponible,context)
            if (!clientsUseCase.isNullOrEmpty())
                period.postValue(periodUC)

            val tarifasUseCase = getTarifasUseCase(isNetDisponible,context)
            if(!tarifasUseCase.isNullOrEmpty())
                tarifas.postValue(tarifasUseCase)

            if (isNetDisponible)
                pushAllsLecturasUseCase(context)

            if (!clientsUseCase.isNullOrEmpty())
                getAllLecturas()

            if(!clientsUseCase.isNullOrEmpty())
                getAllLastLecturas()

            loading.postValue(false)
        }
    }
    fun getAllLecturas(){
        viewModelScope.launch {
            val list = mutableListOf<Lectura>()
            users.value!!.forEach {
                list.addAll(getLecturasUseCase(it.id))
            }
            lecturas.postValue(list)
        }
    }
    fun getAllLastLecturas(){
        viewModelScope.launch {
            val list = mutableListOf<Lectura>()
            users.value!!.forEach {
                list.add(getLastLecturaFromDatabaseUseCase(it.id))
            }
            lastsLecturas.postValue(list)
        }
    }
    fun getClientsByNameOrCount(value: String, isExactly: Boolean){
        viewModelScope.launch {
            val clientsUseCase = getClientByNameOrCountUseCase(value,isExactly)
            if (!clientsUseCase.isNullOrEmpty())
                users.postValue(clientsUseCase)
        }
    }
}