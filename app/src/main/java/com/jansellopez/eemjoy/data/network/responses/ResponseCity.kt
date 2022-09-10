package com.jansellopez.eemjoy.data.network.responses

import com.google.gson.annotations.SerializedName
import com.jansellopez.eemjoy.data.network.modelnetwork.CityNetwork

data class ResponseCity(
    @SerializedName("municipios")val cities: List<CityNetwork>
)
