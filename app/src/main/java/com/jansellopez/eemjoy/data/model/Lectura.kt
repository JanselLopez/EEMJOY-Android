package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.LecturaEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.LecturaNetwork


data class Lectura(
    val id:Int,
    val client_id: Int,
    val address_id:Int,
    val configuracion_id:Int,
    var lectura_anterior:Int?,
    val state:String,
    var lectura_actual:Int,
    var kilovatios:Int,
    var tarifa_id:Int,
    val created_by:Int,
    val created_at:String,
    var agregada:Int,
    var id_add:Int?
):Comparable<Lectura> {
    override fun compareTo(other: Lectura): Int = if(client_id<other.client_id) -1 else if(client_id>other.client_id) 1 else 0
}

fun LecturaNetwork.toDomain() = Lectura(id, client_id, address_id, configuracion_id, lectura_anterior, state, lectura_actual, kilovatios, tarifa_id, created_by, created_at,1,null)
fun LecturaEntity.toDomain() = Lectura(id, client_id, address_id, configuracion_id, lectura_anterior, state, lectura_actual, kilovatios, tarifa_id, created_by, created_at,agregada,id_add)