package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.network.modelnetwork.UserNetwork

data class User(
    val email:String,
    val password:String
)

fun UserNetwork.toDomain() = User(email, password)