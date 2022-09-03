package com.jansellopez.eemjoy.data.model

import java.util.*

data class Lectura(
    val clientCounter:Int,
    val tarifa:Tarifa,
    val lecturaAnterior:Int,
    val lecturaActual:Int,
    val kilobatios:Int,
    val state:String,
    val created:Calendar,
    val update:Calendar,
    val user:Int
)
