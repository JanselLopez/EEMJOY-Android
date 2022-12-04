package com.jansellopez.eemjoy.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.domain.GetCitiesUseCase
import com.jansellopez.eemjoy.domain.PushAllsLecturasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val pushAllsLecturasUseCase: PushAllsLecturasUseCase
) : ViewModel() {

    val cities = MutableLiveData<List<City>>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(isNetDisponible: Boolean,context: Context) {
        viewModelScope.launch {
            loading.postValue(true)
            val citiesUseCase = getCitiesUseCase(isNetDisponible,context)
            if (!citiesUseCase.isNullOrEmpty())
                cities.postValue(citiesUseCase)
            if (isNetDisponible)
                pushAllsLecturasUseCase(context)
            loading.postValue(false)

        }
    }
}