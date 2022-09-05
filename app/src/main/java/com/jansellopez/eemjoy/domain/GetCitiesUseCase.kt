package com.jansellopez.eemjoy.domain

import android.net.ConnectivityManager
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Token
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(token: Token,connectivityManager: ConnectivityManager):List<City> {
        val citiesList:List<City>
        if(connectivityManager.isActiveNetworkMetered) {
            citiesList = repository.getCitiesFromApi(token)
            repository.deleteAllCitiesFromDatabase()
            repository.pushCitiesToDatabase(citiesList.map { it.toDomain() })
        }else
            citiesList = repository.getCitiesFromDataBase()

        return  citiesList
    }
}