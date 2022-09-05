package com.jansellopez.eemjoy.domain

import android.net.ConnectivityManager
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.dao.ClientDao
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.data.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val repository: ClientRepository,
    private val dao: ClientDao
){

    suspend operator fun invoke(city:Int,token: Token,connectivityManager: ConnectivityManager):List<Zone>{
        val zones = mutableListOf<Zone>()
        val repoZones:List<Zone>
        if (connectivityManager.isActiveNetworkMetered){
            repoZones =repository.getZones(token)

            dao.clearZones()
            dao.insertZones(repoZones.map { it.toDomain() })
        }else
            repoZones = dao.getAllZones()!!.map { it.toDomain() }

        repoZones.forEach {
            if (it !in zones && it.municipalityId == city){
                zones.add(it)
            }
        }

          return  zones
    }
}