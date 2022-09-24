package com.jansellopez.eemjoy.domain

import com.jansellopez.eemjoy.data.ClientRepository
import javax.inject.Inject

class GetMaxIdForAddUseCase @Inject constructor(
    private val repository: ClientRepository
) {
    suspend operator fun invoke() = repository.getMaxIdForAdd()
}