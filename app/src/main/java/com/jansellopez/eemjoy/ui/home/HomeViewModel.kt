package com.jansellopez.eemjoy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.domain.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    val cities = MutableLiveData<List<City>>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(isNetDisponible: Boolean) {
        viewModelScope.launch {
            loading.postValue(true)
            val citiesUseCase = getCitiesUseCase(isNetDisponible)
            if (!citiesUseCase.isNullOrEmpty())
                cities.postValue(citiesUseCase)
            loading.postValue(false)
        }
    }
}