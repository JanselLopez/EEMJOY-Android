package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.network.ClientNetwork

data class Client(
    val id:Int,
    val firstName:String,
    val firstLastName: String,
    val secondLastName:String,
    val numberCount:String
    )

fun ClientNetwork.toDomain() = Client(id = id,firstName= firstName,firstLastName = firstLastName,secondLastName = secondLastName, numberCount= numberCount)