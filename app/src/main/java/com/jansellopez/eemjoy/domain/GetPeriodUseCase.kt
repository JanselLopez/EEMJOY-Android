package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Period
import javax.inject.Inject

class GetPeriodUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(isNetDisponible:Boolean,context: Context):Period= if(isNetDisponible) {
            val period = repository.getPeriodFromApi(context)
            repository.deleteAllPeriodsFromDatabase()
            repository.pushPeriodsToDatabase(period.toDomain())
            period
        }else
            repository.getPeriodsFromDataBase()

}