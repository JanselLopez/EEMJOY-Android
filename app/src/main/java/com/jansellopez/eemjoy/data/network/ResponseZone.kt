package com.jansellopez.eemjoy.data.network

import com.google.gson.annotations.SerializedName

data class ResponseZone (
    @SerializedName("direcciones")val zones:List<ZoneNetwork>
)