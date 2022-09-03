package com.jansellopez.eemjoy.data.model

data class Tarifa (
    val minRange:Int,
    val maxRange:Int,
    val kwh:Double,
    val iva:Double,
    val cargoFijo:Double,
    val cargoFijoIva:Double,
    val aporteInde:Double,
    val aporteIndeIva:Double,
    val alumbradoPublico:Double
)