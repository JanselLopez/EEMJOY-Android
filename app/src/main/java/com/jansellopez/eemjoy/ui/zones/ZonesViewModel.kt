package com.jansellopez.eemjoy.ui.zones

import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.domain.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase
):ViewModel() {

    val zones = MutableLiveData<List<Zone>>()

    fun onCreate(city:Int,token: Token,connectivityManager: ConnectivityManager){
        viewModelScope.launch {
            val zonesUseCase = getZonesUseCase(city,token,connectivityManager)
            if(!zonesUseCase.isNullOrEmpty())
                zones.postValue(zonesUseCase)
        }
    }

}