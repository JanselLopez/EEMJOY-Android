package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.database.entities.toDomain
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Period
import javax.inject.Inject

class GetPeriodUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(isNetDisponible:Boolean):Period= if(isNetDisponible) {
            val period = repository.getPeriodFromApi()
            repository.deleteAllPeriodsFromDatabase()
            repository.pushPeriodsToDatabase(period.toDomain())
            period
        }else
            repository.getPeriodsFromDataBase()

}