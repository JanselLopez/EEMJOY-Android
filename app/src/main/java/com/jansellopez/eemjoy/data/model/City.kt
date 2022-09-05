package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.network.CityNetwork

data class City (
    val id:Int,
    val name:String,
    val state:String
    )

fun CityNetwork.toDomain() = City(id,name, state)