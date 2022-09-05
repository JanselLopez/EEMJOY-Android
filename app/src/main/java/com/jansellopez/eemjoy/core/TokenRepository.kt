package com.jansellopez.eemjoy.core

import com.jansellopez.eemjoy.data.model.Token

object TokenRepository {
    private var token:Token= Token("","")

    fun getToken() = token

    fun setToken(token2: Token) {
        token = token2
    }
}