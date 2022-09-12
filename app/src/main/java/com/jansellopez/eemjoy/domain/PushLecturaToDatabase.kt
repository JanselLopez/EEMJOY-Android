package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Lectura
import javax.inject.Inject

class PushLecturaToDatabase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(lectura:Lectura) = repository.pushLecturaToDatabase(lectura.toDomain())
}