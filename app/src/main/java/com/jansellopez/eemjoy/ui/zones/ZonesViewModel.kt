package com.jansellopez.eemjoy.ui.zones

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.domain.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase
):ViewModel() {

    val zones = MutableLiveData<List<String>>()

    fun onCreate(city:String){
        viewModelScope.launch {
            val zonesUseCase = getZonesUseCase(city)
            if(!zonesUseCase.isNullOrEmpty())
                zones.postValue(zonesUseCase)
        }
    }

}