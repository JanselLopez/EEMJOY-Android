package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke(){

    }
}