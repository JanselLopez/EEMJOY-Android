package com.jansellopez.eemjoy.data.network

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("municipio")val city:Int,
    @SerializedName("direccion")val address: Int
)
