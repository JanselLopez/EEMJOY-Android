package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Token
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ClientRepository
){
    suspend operator fun invoke(token: Token):List<City> = repository.getCities(token)
}