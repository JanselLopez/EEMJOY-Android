package com.jansellopez.eemjoy.domain

import android.content.Context
import com.jansellopez.eemjoy.data.ClientRepository
import javax.inject.Inject

class GetTarifaOfLecturaUseCase @Inject constructor(
    private val getTarifasUseCase: GetTarifasUseCase
) {
    suspend operator fun invoke(isConnect:Boolean,context: Context,kilovatios:Int):Int{
        var tarifaId = 1
        val tarifasUseCase = getTarifasUseCase(isConnect, context)

        run breaking@{
            tarifasUseCase.forEach {
                if (it.range_min <= kilovatios && it.range_max >= kilovatios) {
                    tarifaId = it.id
                    return@breaking
                }
            }
        }
        return tarifaId
    }
}