package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.LecturaAdd
import javax.inject.Inject

class UpdateLecturaUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(lectura:Lectura,lecturaAdd: LecturaAdd) {
        repository.updateLectura(lectura)
        repository.updateLecturaAdd(lecturaAdd.toDomain())
    }
}