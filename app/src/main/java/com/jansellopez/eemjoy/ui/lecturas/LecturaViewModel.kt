package com.jansellopez.eemjoy.ui.lecturas

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.LecturaAdd
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturaViewModel @Inject constructor(
    private val getLecturasUseCase: GetLecturasUseCase,
    private val getPeriodFromDataBaseUseCase: GetPeriodFromDataBaseUseCase,
    private val pushLecturaToDatabase: PushLecturaToDatabase,
    private val getLastLecturaFromDatabaseUseCase: GetLastLecturaFromDatabaseUseCase,
    private val pushOneLecturaToDatabaseUseCase: PushOneLecturaToDatabaseUseCase,
    private val pushOneLecturaToApi: PushOneLecturaToApi
):ViewModel() {
    val lecturas = MutableLiveData<List<Lectura>>()
    val loading = MutableLiveData<Boolean>()
    val lastLectura = MutableLiveData<Lectura>()
    val period = MutableLiveData<Period>()

    fun onCreate(clientId: Int){
        lecturas.postValue(mutableListOf())
             viewModelScope.launch {
                 loading.postValue(true)
                 val lecturasUseCase = getLecturasUseCase(clientId)
                 period.postValue(getPeriodFromDataBaseUseCase()!!)
                 lastLectura.postValue(getLastLecturaFromDatabaseUseCase(clientId)!!)
                 if(!lecturasUseCase.isNullOrEmpty())
                     lecturas.postValue(lecturasUseCase)
                 loading.postValue(false)
             }
    }
    fun pushLectura(lectura: Lectura, context: Context, numberCount: Int,clientId: Int){
        viewModelScope.launch {
            loading.postValue(true)
            if (CheckConnect(context)){
                Log.e("Lectura Add ViewModel","${lectura.configuracion_id} $numberCount ${lectura.kilovatios} ${lectura.lectura_anterior?:0}")
                pushOneLecturaToApi(LecturaAdd(lectura.configuracion_id,numberCount,lectura.lectura_actual,lectura.lectura_anterior?:0),context)
            }else{
                pushOneLecturaToDatabaseUseCase(LecturaAdd(lectura.configuracion_id,numberCount,lectura.lectura_actual,lectura.lectura_anterior?:0))
            }
            pushLecturaToDatabase(lectura)
            lecturas.postValue(getLecturasUseCase(clientId)!!)
            lastLectura.postValue(getLastLecturaFromDatabaseUseCase(clientId)!!)
            loading.postValue(false)
        }
    }

}