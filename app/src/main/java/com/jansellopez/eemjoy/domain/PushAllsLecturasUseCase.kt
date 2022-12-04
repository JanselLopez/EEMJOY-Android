package com.jansellopez.eemjoy.domain

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.network.responses.ResponseAddLecturas
import cu.jansellopez.custom_snackbars.CustomSnackBar
import javax.inject.Inject

class PushAllsLecturasUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(context: Context){
            val lecturasAdd = repository.getLecturasFromDataBase()
                    lecturasAdd.forEach {
                    val response = repository.pushLecturaToApi(it,context)
                    Toast.makeText(context,response.report,Toast.LENGTH_LONG).show()
                        repository.deleteLecturaAdd(it.id)
                }

        repository.clearLecturasAdd()
    }
}