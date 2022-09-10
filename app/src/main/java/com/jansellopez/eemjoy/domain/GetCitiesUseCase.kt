package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(isNetDisponible: Boolean):List<City> {
        val citiesList:List<City>
        if(isNetDisponible) {
            citiesList = repository.getCitiesFromApi()
            repository.deleteAllCitiesFromDatabase()
            repository.pushCitiesToDatabase(citiesList.map { it.toDomain() })
        }else
            citiesList = repository.getCitiesFromDataBase()

        return  citiesList
    }
}