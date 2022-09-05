package com.jansellopez.eemjoy.data.database.dao

import androidx.room.*
import com.jansellopez.eemjoy.data.database.entities.CityEntity
import com.jansellopez.eemjoy.data.database.entities.ZoneEntity

@Dao
interface ClientDao {

    @Query("SELECT * FROM city_table")
    suspend fun getAllCities():List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(citiesList:List<CityEntity>)

    @Query("DELETE FROM city_table")
    suspend fun clearCities()

    @Query("SELECT * FROM zone_table")
    suspend fun getAllZones():List<ZoneEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZones(zonesList:List<ZoneEntity>)

    @Query("DELETE FROM zone_table")
    suspend fun clearZones()

}