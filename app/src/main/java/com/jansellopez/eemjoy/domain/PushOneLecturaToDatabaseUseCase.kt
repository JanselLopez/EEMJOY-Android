package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.LecturaAdd
import javax.inject.Inject

class PushOneLecturaToDatabaseUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(lecturaAdd: LecturaAdd) =repository.pushLecturaForAddToDatabase(lecturaAdd.toDomain())
}