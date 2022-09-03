package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(user: User): Token {
       return repository.login(user)
    }
}