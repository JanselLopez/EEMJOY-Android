package com.jansellopez.eemjoy.data.network

import com.google.gson.annotations.SerializedName

data class ResponseCity(
    @SerializedName("municipios")val cities: List<CityNetwork>
)
