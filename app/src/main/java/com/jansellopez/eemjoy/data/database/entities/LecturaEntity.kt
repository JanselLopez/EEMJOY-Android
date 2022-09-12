package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Tarifa
import java.util.*

@Entity(tableName = "lecturas_table")
data class LecturaEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="client_id")val client_id: Int,
    @ColumnInfo(name="address_id")val address_id:Int,
    @ColumnInfo(name="configuracion_id")val configuracion_id:Int,
    @ColumnInfo(name="lectura_anterior")val lectura_anterior:Int?,
    @ColumnInfo(name="state")val state:String,
    @ColumnInfo(name="lectura_actual")val lectura_actual:Int,
    @ColumnInfo(name="kilovatios")val kilovatios:Int,
    @ColumnInfo(name="tarifa_id")val tarifa_id:Int,
    @ColumnInfo(name="created_by")val created_by:Int,
    @ColumnInfo(name="created_at")val created_at:String
)

fun Lectura.toDomain() = LecturaEntity(id, client_id, address_id, configuracion_id, lectura_anterior, state, lectura_actual, kilovatios, tarifa_id,created_by, created_at)

