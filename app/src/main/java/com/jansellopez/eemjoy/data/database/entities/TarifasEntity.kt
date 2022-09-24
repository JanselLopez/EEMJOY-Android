package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Tarifa

@Entity(tableName = "tarifas_table")
data class TarifasEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="range_max")val range_max:Long,
    @ColumnInfo(name="range_min")val range_min:Int,
    @ColumnInfo(name="kwh")val kwh:Double,
    @ColumnInfo(name="iva")val iva:Double,
    @ColumnInfo(name="fixed_charge")val fixed_charge:Double,
    @ColumnInfo(name="fixed_charge_iva")val fixed_charge_iva:Double,
    @ColumnInfo(name="street_lighting")val street_lighting:Double,
    @ColumnInfo(name="inde_contribution")val inde_contribution:Double,
    @ColumnInfo(name="inde_contribution_iva")val inde_contribution_iva:Double,
    @ColumnInfo(name="mora")val mora:Double,
    @ColumnInfo(name="state")val state:String,
    @ColumnInfo(name="cobro_real")val cobro_real:String,
    @ColumnInfo(name="periodo_id")val periodo_id:Int
)
fun Tarifa.toDomain() = TarifasEntity(id, range_max, range_min, kwh, iva, fixed_charge, fixed_charge_iva, street_lighting, inde_contribution, inde_contribution_iva, mora, state, cobro_real, periodo_id)