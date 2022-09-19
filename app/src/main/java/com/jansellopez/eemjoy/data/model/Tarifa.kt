package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.PeriodEntity
import com.jansellopez.eemjoy.data.database.entities.TarifasEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.PeriodNetwork
import com.jansellopez.eemjoy.data.network.modelnetwork.TarifasNetwork
import java.util.*

data class Tarifa (
    val id:Int,
    val range_max:Long,
    val range_min:Int,
    val kwh:Double,
    val iva:Int,
    val fixed_charge:Double,
    val fixed_charge_iva:Int,
    val street_lighting:Double,
    val inde_contribution:Double,
    val inde_contribution_iva:Int,
    val mora:Int,
    val state:String,
    val cobro_real:String,
    val periodo_id:Int
)
fun TarifasNetwork.toDomain() = Tarifa(id, range_max, range_min, kwh, iva, fixed_charge, fixed_charge_iva, street_lighting, inde_contribution, inde_contribution_iva, mora, state, cobro_real, periodo_id)
fun TarifasEntity.toDomain() = Tarifa(id, range_max, range_min, kwh, iva, fixed_charge, fixed_charge_iva, street_lighting, inde_contribution, inde_contribution_iva, mora, state, cobro_real, periodo_id)