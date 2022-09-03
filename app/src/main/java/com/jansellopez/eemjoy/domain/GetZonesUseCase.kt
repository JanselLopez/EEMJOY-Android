package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val repository: ClientRepository
){

    suspend operator fun invoke(city:String):List<String>{
       /* val zones = mutableListOf<String>()
            repository.getAllUsers().forEach {
                if (it.address !in zones && it.city == city){
                    zones.add(it.address)
                }
            }
          return  zones*/
        return emptyList()
    }
}