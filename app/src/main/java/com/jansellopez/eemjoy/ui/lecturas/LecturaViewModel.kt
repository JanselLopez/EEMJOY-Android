package com.jansellopez.eemjoy.ui.lecturas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.domain.GetLastLecturaFromDatabaseUseCase
import com.jansellopez.eemjoy.domain.GetLecturasUseCase
import com.jansellopez.eemjoy.domain.GetPeriodFromDataBaseUseCase
import com.jansellopez.eemjoy.domain.PushLecturaToDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturaViewModel @Inject constructor(
    private val getLecturasUseCase: GetLecturasUseCase,
    private val getPeriodFromDataBaseUseCase: GetPeriodFromDataBaseUseCase,
    private val pushLecturaToDatabase: PushLecturaToDatabase,
    private val getLastLecturaFromDatabaseUseCase: GetLastLecturaFromDatabaseUseCase
):ViewModel() {
    val lecturas = MutableLiveData<List<Lectura>>()
    val loading = MutableLiveData<Boolean>()
    val lastLectura = MutableLiveData<Lectura>()
    val period = MutableLiveData<Period>()

    fun onCreate(clientId: Int){
             viewModelScope.launch {
                 loading.postValue(true)
                 val lecturasUseCase = getLecturasUseCase(clientId)
                 period.postValue(getPeriodFromDataBaseUseCase())
                 lastLectura.postValue(getLastLecturaFromDatabaseUseCase(clientId))
                 if(!lecturasUseCase.isNullOrEmpty())
                     lecturas.postValue(lecturasUseCase)
                 loading.postValue(false)
             }
    }
    fun pushLectura(lectura: Lectura,clientId: Int){
        viewModelScope.launch {
            pushLecturaToDatabase(lectura)
            onCreate(clientId)
        }
    }

}