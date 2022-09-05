package com.jansellopez.eemjoy.data.network

import com.jansellopez.eemjoy.data.model.Token

data class TokenNetwork(
    val access_token:String,
    val token_type:String
)

fun Token.toDomain() = TokenNetwork(access_token, token_type)
