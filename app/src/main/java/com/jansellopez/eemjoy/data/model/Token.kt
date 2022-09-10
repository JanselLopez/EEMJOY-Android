package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.network.modelnetwork.TokenNetwork

data class Token(
    val access_token:String,
    val token_type:String
)

fun TokenNetwork.toDomain() = Token(access_token,token_type)
