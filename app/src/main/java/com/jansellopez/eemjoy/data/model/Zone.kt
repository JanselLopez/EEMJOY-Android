package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.ZoneEntity
import com.jansellopez.eemjoy.data.network.ZoneNetwork

data class Zone(
    val id:Int,
    val municipalityId:Int,
    val address: String,
    val state:String
)

fun ZoneNetwork.toDomain() = Zone(id, municipalityId, address, state)

fun ZoneEntity.toDomain() = Zone(id, municipalityId, address, state)
