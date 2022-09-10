package com.jansellopez.eemjoy.data.network.modelnetwork

import com.google.gson.annotations.SerializedName
import com.jansellopez.eemjoy.data.model.User

data class UserNetwork(
    val email:String,
    val password:String
)

fun User.toDomain() = UserNetwork(email, password)
