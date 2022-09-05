package com.jansellopez.eemjoy.ui.home

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.domain.GetCitiesUseCase
import com.jansellopez.eemjoy.domain.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    val cities = MutableLiveData<List<City>>()

    fun onCreate(token:Token,connectivityManager: ConnectivityManager) {
        viewModelScope.launch {
            val citiesUseCase = getCitiesUseCase(token,connectivityManager)
            if (!citiesUseCase.isNullOrEmpty())
                cities.postValue(citiesUseCase)
        }
    }
}