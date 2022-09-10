package com.jansellopez.eemjoy.data.database.entities

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jansellopez.eemjoy.data.model.Client

@Entity(tableName = "clients_table")
data class ClientEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="firstName")val firstName:String,
    @ColumnInfo(name="firstLastName")val firstLastName: String,
    @ColumnInfo(name="secondLastName")val secondLastName:String,
    @ColumnInfo(name="numberCount")val numberCount:String,
    @ColumnInfo(name = "city")val city:Int,
    @ColumnInfo(name = "address")val address:Int,
)

fun Client.toDomain(city: Int,zone:Int) = ClientEntity(id, firstName, firstLastName, secondLastName, numberCount,city,zone)
