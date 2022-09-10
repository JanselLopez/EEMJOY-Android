package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.data.model.Zone
import java.util.*

@Entity(tableName = "periods_table")
data class PeriodEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id") val id:Int,
    @ColumnInfo(name="title")  val title:String,
    @ColumnInfo(name="beginDate") val beginDate: Long,
    @ColumnInfo(name="endDate") val endDate: Long,
    @ColumnInfo(name="paymentDate") val paymentDate: Long
    )

fun Period.toDomain() = PeriodEntity(id, title,beginDate.timeInMillis,endDate.timeInMillis,paymentDate.timeInMillis)