package com.jansellopez.eemjoy.data.network.modelnetwork

import java.util.*

data class LecturaNetwork(
    val id:Int,
    val client_id: Int,
    val address_id:Int,
    val configuracion_id:Int,
    val lectura_anterior:Int?,
    val state:String,
    val lectura_actual:Int,
    val kilovatios:Int,
    val tarifa_id:Int
)