package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Zone
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val repository: ClientRepository,
){

    suspend operator fun invoke(city: Int, isNetDisponible: Boolean,context: Context):List<Zone>{
        val zones = mutableListOf<Zone>()
        val repoZones:List<Zone>
        if (isNetDisponible){
            repoZones =repository.getZones(context)
            repository.pushZonesToDatabase(repoZones.map { it.toDomain() })
        }else
            repoZones = repository.getZonesFromDataBase()

        repoZones.forEach {
            if (it !in zones && it.municipalityId == city){
                zones.add(it)
            }
        }

          return  zones
    }
}