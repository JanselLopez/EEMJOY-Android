package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Tarifa
import javax.inject.Inject

class GetTarifasUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(isNetDisponible: Boolean,context: Context):List<Tarifa> =  if(isNetDisponible) {
            val tarifas = repository.getTarifasFromApi(context)
            repository.deleteAllTarifasFromDatabase()
            repository.pushTarifasToDatabase(tarifas.map { it.toDomain() })
            tarifas
        }else
            repository.getTarifasFromDataBase()



}