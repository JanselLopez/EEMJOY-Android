package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke():List<String>{
        /*val cities = mutableListOf<String>()
        repository.getAllUsers().forEach {
            if (it.city !in cities){
                cities.add(it.city)
            }
        }
        return  cities*/
        return emptyList()
    }
}