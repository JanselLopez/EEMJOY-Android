package com.jansellopez.eemjoy.ui.zones

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.domain.GetZonesUseCase
import com.jansellopez.eemjoy.domain.PushAllsLecturasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase,
    private val pushAllsLecturasUseCase: PushAllsLecturasUseCase
):ViewModel() {

    val zones = MutableLiveData<List<Zone>>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(city: Int, isNetDisponible: Boolean,context: Context){
        viewModelScope.launch {
            loading.postValue(true)
            val zonesUseCase = getZonesUseCase(city,isNetDisponible,context)
            if(!zonesUseCase.isNullOrEmpty())
                zones.postValue(zonesUseCase)
            if (isNetDisponible)
                pushAllsLecturasUseCase(context)
            loading.postValue(false)
        }
    }

}