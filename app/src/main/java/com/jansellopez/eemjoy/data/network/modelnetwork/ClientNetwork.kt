package com.jansellopez.eemjoy.data.network.modelnetwork

import com.google.gson.annotations.SerializedName

data class ClientNetwork(
    val id:Int,
    val first_name:String?,
    val first_lastname: String,
    val second_lastname:String?,
    val number_cont:String
)
