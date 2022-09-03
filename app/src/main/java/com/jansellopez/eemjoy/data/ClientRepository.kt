package com.jansellopez.eemjoy.data

import com.jansellopez.eemjoy.data.model.*
import com.jansellopez.eemjoy.data.network.UserService
import com.jansellopez.eemjoy.data.network.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val userService: UserService
) {

    suspend fun login(user: User): Token =
        withContext(Dispatchers.IO) {
            userService.login(user.toDomain()).toDomain()
        }

}


