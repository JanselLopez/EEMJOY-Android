package com.jansellopez.eemjoy.data.network

import com.google.gson.annotations.SerializedName

data class ResponseClient (
    @SerializedName("clientes") val clients:List<ClientNetwork>
    )