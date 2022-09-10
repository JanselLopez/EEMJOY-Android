package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Client
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(city: Int, zone: Int, isNetDisponible: Boolean):List<Client>{
        val clients:List<Client>
        if(isNetDisponible) {
            clients = repository.getClientsFromApi(city, zone)
            repository.deleteAllClientsFromDatabase()
            repository.pushClientsToDatabase(clients.map { it.toDomain(city, zone) })
            val lecturas = repository.getLecturasFromApi(zone)
            repository.deleteAllLecturasFromDatabase()
            repository.pushLecturasToDatabase(lecturas.map { it.toDomain() })
        }else
            clients = repository.getClientsFromDataBase(city, zone)

        return  clients
    }
}