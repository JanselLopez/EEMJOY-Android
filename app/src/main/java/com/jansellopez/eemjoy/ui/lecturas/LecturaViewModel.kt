package com.jansellopez.eemjoy.ui.lecturas

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.LecturaAdd
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.domain.*
import cu.jansellopez.custom_snackbars.CustomSnackBar
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
    private val pushOneLecturaToApi: PushOneLecturaToApi,
    private val getTarifaOfLecturaUseCase: GetTarifaOfLecturaUseCase,
    private val updateLecturaUseCase: UpdateLecturaUseCase,
    private val getMaxIdForAddUseCase: GetMaxIdForAddUseCase,
    private val pushAllsLecturasUseCase: PushAllsLecturasUseCase,
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
    fun pushLectura(lectura: Lectura, context: Context, numberCount: Int,clientId: Int,activity: Activity,coordinatorLayout: CoordinatorLayout){
        viewModelScope.launch {
            loading.postValue(true)
            val idForAdd = getMaxIdForAddUseCase()+1
            val tarifa = getTarifaOfLecturaUseCase(CheckConnect(context),context,lectura.kilovatios)
            lectura.tarifa_id = tarifa
            Log.e("Tarifa",tarifa.toString() + " of "+lectura.kilovatios )
            if (CheckConnect(context)){
                Log.e("Lectura Add ViewModel","${lectura.configuracion_id} $numberCount ${lectura.kilovatios} ${lectura.lectura_anterior?:0}")
                val response = pushOneLecturaToApi(LecturaAdd(0,lectura.configuracion_id,numberCount,lectura.lectura_actual,lectura.lectura_anterior?:0),context)
                lectura.agregada =1
                Toast.makeText(context,response.report,Toast.LENGTH_SHORT).show()
            }else{
                pushOneLecturaToDatabaseUseCase(LecturaAdd(idForAdd,lectura.configuracion_id,numberCount,lectura.lectura_actual,lectura.lectura_anterior?:0))
            }
            lectura.id_add = idForAdd
            pushLecturaToDatabase(lectura)
            lecturas.postValue(getLecturasUseCase(clientId)!!)
            lastLectura.postValue(getLastLecturaFromDatabaseUseCase(clientId)!!)
            loading.postValue(false)
        }
    }

    fun update(lectura: Lectura, clientId: Int,id:Int,numberCount: Int){
        viewModelScope.launch {
            loading.postValue(true)
            updateLecturaUseCase(lectura, LecturaAdd(id,lectura.configuracion_id,numberCount,lectura.kilovatios,lectura.lectura_anterior?:0))
            lecturas.postValue(getLecturasUseCase(clientId)!!)
            loading.postValue(false)
        }
    }

    fun pushAll(context: Context){
        viewModelScope.launch {
            if (CheckConnect(context))
                pushAllsLecturasUseCase(context)
        }
    }

}