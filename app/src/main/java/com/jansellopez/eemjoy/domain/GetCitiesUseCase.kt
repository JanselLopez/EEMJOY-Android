package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(isNetDisponible: Boolean,context: Context):List<City> {
        var citiesList:List<City>
        if(isNetDisponible) {
                citiesList = repository.getCitiesFromApi(context)
                repository.pushCitiesToDatabase(citiesList.map { it.toDomain() })
        }else
            citiesList = repository.getCitiesFromDataBase()

        return  citiesList
    }
}