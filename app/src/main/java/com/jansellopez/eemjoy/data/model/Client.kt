package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.ClientEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.ClientNetwork

data class Client(
    val id:Int,
    val firstName:String,
    val firstLastName: String,
    val secondLastName:String,
    val numberCount:String
    )

fun ClientNetwork.toDomain() = Client(id = id,firstName= first_name?:"",firstLastName = first_lastname,secondLastName = second_lastname?:"", numberCount= number_cont)
fun ClientEntity.toDomain() = Client(id,firstName,firstLastName,secondLastName, numberCount)