package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.network.TokenNetwork

data class Token(
    val access_token:String
)

fun TokenNetwork.toDomain() = Token(access_token)
