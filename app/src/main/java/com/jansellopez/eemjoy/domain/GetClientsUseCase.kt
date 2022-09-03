package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.model.Client
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(city:String,zone:String):List<Client>{
        /*val users = mutableListOf<Client>()
        repository.getAllUsers().forEach {
            if(it.city==city && it.address == zone)
               users.add(it)
        }
        return users*/
        return emptyList()
    }
}