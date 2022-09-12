package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import javax.inject.Inject

class GetLastLecturaFromDatabaseUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(clientId:Int) = repository.getLastLecturaFromDataBase(clientId)
}