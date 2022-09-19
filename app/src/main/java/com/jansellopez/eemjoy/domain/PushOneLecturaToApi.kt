package com.jansellopez.eemjoy.domain

import android.content.Context
import android.widget.Toast
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.LecturaAdd
import javax.inject.Inject

class PushOneLecturaToApi @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(lecturaAdd: LecturaAdd,context:Context) {
        val response = repository.pushLecturaToApi(lecturaAdd, context)
        Toast.makeText(context,response.report, Toast.LENGTH_LONG).show()
    }
}