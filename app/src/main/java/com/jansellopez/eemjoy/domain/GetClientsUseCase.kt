package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Client
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(city: Int, zone: Int, isNetDisponible: Boolean,context: Context){
        val clients:List<Client>
        if(isNetDisponible) {
                clients = repository.getClientsFromApi(city, zone, context)
                repository.pushClientsToDatabase(clients.map { it.toDomain(city, zone) })
                val lecturas = repository.getLecturasFromApi(zone, context)
                repository.pushLecturasToDatabase(lecturas.map { it.toDomain() })
        }
    }
}