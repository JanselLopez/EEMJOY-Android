package com.jansellopez.eemjoy.ui.lecturas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.domain.GetLecturasUseCase
import com.jansellopez.eemjoy.domain.GetPeriodFromDataBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturaViewModel @Inject constructor(
    private val getLecturasUseCase: GetLecturasUseCase,
    private val getPeriodFromDataBaseUseCase: GetPeriodFromDataBaseUseCase
):ViewModel() {
    val lecturas = MutableLiveData<List<Lectura>>()
    val loading = MutableLiveData<Boolean>()
    val period = MutableLiveData<Period>()

    fun onCreate(clientId: Int){
             viewModelScope.launch {
                 loading.postValue(true)
                 val lecturasUseCase = getLecturasUseCase(clientId)
                 period.postValue(getPeriodFromDataBaseUseCase()!!)
                 if(!lecturasUseCase.isNullOrEmpty())
                     lecturas.postValue(lecturasUseCase)
                 loading.postValue(false)
             }
    }

}