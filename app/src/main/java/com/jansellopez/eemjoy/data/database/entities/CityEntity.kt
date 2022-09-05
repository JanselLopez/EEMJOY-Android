package com.jansellopez.eemjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.City

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="name")val name:String,
    @ColumnInfo(name="state")val state:String
)

fun City.toDomain() = CityEntity(id, name, state)
