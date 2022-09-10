package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.LecturaEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.LecturaNetwork


data class Lectura(
    val id:Int,
    val client_id: Int,
    val address_id:Int,
    val configuracion_id:Int,
    val lectura_anterior:Int?,
    val state:String,
    val lectura_actual:Int,
    val kilovatios:Int,
    val tarifa_id:Int,
)

fun LecturaNetwork.toDomain() = Lectura(id, client_id, address_id, configuracion_id, lectura_anterior, state, lectura_actual, kilovatios, tarifa_id)
fun LecturaEntity.toDomain() = Lectura(id, client_id, address_id, configuracion_id, lectura_anterior, state, lectura_actual, kilovatios, tarifa_id)