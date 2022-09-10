package com.jansellopez.eemjoy.data.network.responses

import com.google.gson.annotations.SerializedName
import com.jansellopez.eemjoy.data.network.modelnetwork.ClientNetwork

data class ResponseClient (
    @SerializedName("clientes") val clients:List<ClientNetwork>
    )