package com.jansellopez.eemjoy.data.network.responses

import com.google.gson.annotations.SerializedName
import com.jansellopez.eemjoy.data.network.modelnetwork.ZoneNetwork

data class ResponseZone (
    @SerializedName("direcciones")val zones:List<ZoneNetwork>
)