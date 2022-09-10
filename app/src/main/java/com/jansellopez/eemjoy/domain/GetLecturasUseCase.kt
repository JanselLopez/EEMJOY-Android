package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Lectura
import javax.inject.Inject

class GetLecturasUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(client_id: Int): List<Lectura> = repository.getLecturasFromDataBase(client_id)
}