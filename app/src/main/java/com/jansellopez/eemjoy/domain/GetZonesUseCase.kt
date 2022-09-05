package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.Zone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val repository: ClientRepository
){

    suspend operator fun invoke(city:Int,token: Token):List<Zone>{
        val zones = mutableListOf<Zone>()
            repository.getZones(token).forEach {
                if (it !in zones && it.municipalityId == city){
                    zones.add(it)
                }
            }
          return  zones
    }
}