package com.jansellopez.eemjoy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jansellopez.eemjoy.data.database.dao.ClientDao
import com.jansellopez.eemjoy.data.database.entities.*

@Database(entities = [CityEntity::class,ZoneEntity::class,ClientEntity::class,LecturaEntity::class,PeriodEntity::class,LecturaEntityAdd::class,TarifasEntity::class],version = 1)
abstract class ClientDatabase:RoomDatabase() {
    abstract fun getClientsDao(): ClientDao
}