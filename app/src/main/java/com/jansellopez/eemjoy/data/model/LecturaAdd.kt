package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.LecturaEntityAdd
import com.jansellopez.eemjoy.data.network.modelnetwork.LecturaNetworkAdd

class LecturaAdd (
    var id:Int,
    val configuracion_id:Int,
    val number_cont:Int,
    val kilovatios:Int,
    val lectura_anterior:Int
    )


fun LecturaEntityAdd.toDomain() = LecturaAdd(id,configuracion_id, number_cont, kilovatios, lectura_anterior)
fun LecturaNetworkAdd.toDomain() = LecturaAdd(0,configuracion_id, number_cont, kilovatios, lectura_anterior)


