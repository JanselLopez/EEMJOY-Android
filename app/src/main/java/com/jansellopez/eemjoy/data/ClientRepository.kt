package com.jansellopez.eemjoy.data

import android.util.Log
import com.jansellopez.eemjoy.data.model.*
import com.jansellopez.eemjoy.data.network.UserService
import com.jansellopez.eemjoy.data.network.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val userService: UserService
) {

    suspend fun login(user: User): Token = userService.login(user.toDomain()).toDomain()

    suspend fun getCities(token: Token):List<City> = userService.getCities(token).map { it.toDomain() }

    suspend fun getZones(token: Token):List<Zone> = userService.getZones(token).map { it.toDomain() }



}