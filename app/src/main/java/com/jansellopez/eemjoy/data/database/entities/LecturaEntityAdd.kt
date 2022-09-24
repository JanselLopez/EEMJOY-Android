package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.LecturaAdd

@Entity(tableName = "lecturas_add_table")
data class LecturaEntityAdd (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")var id:Int,
    @ColumnInfo(name="configuracion_id") val configuracion_id:Int,
    @ColumnInfo(name="number_cont")val number_cont:Int,
    @ColumnInfo(name="kilovatios")val kilovatios:Int,
    @ColumnInfo(name="lectura_anterior")val lectura_anterior:Int
)
fun LecturaAdd.toDomain() = LecturaEntityAdd(id,configuracion_id, number_cont,kilovatios,lectura_anterior)