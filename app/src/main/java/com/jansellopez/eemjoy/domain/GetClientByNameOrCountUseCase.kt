package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.model.Client
import javax.inject.Inject

class GetClientByNameOrCountUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(value: String, isExact:Boolean):List<Client> = if(isExact) repository.getClientsByNameOrCountExactly(
        value
    ) else repository.getClientsByNameOrCount(value)
}