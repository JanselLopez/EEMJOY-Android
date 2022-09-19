package com.jansellopez.eemjoy.data.network.modelnetwork

import com.jansellopez.eemjoy.data.model.LecturaAdd
import com.jansellopez.eemjoy.data.model.Period

data class LecturaNetworkAdd (
    val configuracion_id:Int,
    val number_cont:Int,
    val kilovatios:Int,
    val lectura_anterior:Int
)

fun LecturaAdd.toDomain() = LecturaNetworkAdd(configuracion_id, number_cont, kilovatios, lectura_anterior)