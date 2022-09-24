package com.jansellopez.eemjoy.data.network.modelnetwork

import com.jansellopez.eemjoy.data.model.Tarifa
import com.jansellopez.eemjoy.data.model.Token

data class TarifasNetwork (
    val id:Int,
    val range_max:Long,
    val range_min:Int,
    val kwh:Double,
    val iva:Double,
    val fixed_charge:Double,
    val fixed_charge_iva:Double,
    val street_lighting:Double,
    val inde_contribution:Double,
    val inde_contribution_iva:Double,
    val mora:Double,
    val state:String,
    val cobro_real:String,
    val periodo_id:Int
)

fun Tarifa.toDomain() = TarifasNetwork(id, range_max, range_min, kwh, iva, fixed_charge, fixed_charge_iva, street_lighting, inde_contribution, inde_contribution_iva, mora, state, cobro_real, periodo_id)