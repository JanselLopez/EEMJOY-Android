package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.LecturaEntityAdd
import com.jansellopez.eemjoy.data.network.modelnetwork.LecturaNetworkAdd

class LecturaAdd (
    val configuracion_id:Int,
    val number_cont:Int,
    val kilovatios:Int,
    val lectura_anterior:Int
    )

fun LecturaNetworkAdd.toDomain() = LecturaAdd(configuracion_id, number_cont, kilovatios, lectura_anterior)
fun LecturaEntityAdd.toDomain() = LecturaAdd(configuracion_id, number_cont, kilovatios, lectura_anterior)

