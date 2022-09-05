package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Zone

@Entity(tableName = "zone_table")
data class ZoneEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="municipalityId")val municipalityId:Int,
    @ColumnInfo(name="address")val address: String,
    @ColumnInfo(name="state")val state:String
    )

fun Zone.toDomain() = ZoneEntity(id, municipalityId, address, state)