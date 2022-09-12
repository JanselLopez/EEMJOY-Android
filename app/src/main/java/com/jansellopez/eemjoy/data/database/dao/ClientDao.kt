package com.jansellopez.eemjoy.data.database.dao

import androidx.room.*
import com.jansellopez.eemjoy.data.database.entities.*

@Dao
interface ClientDao {
    //cities
    @Query("SELECT * FROM city_table")
    suspend fun getAllCities():List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(citiesList:List<CityEntity>)

    @Query("DELETE FROM city_table")
    suspend fun clearCities()

    //zones
    @Query("SELECT * FROM zone_table")
    suspend fun getAllZones():List<ZoneEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZones(zonesList:List<ZoneEntity>)

    @Query("DELETE FROM zone_table")
    suspend fun clearZones()

    //clients
    @Query("SELECT * FROM clients_table WHERE city=:city and address =:zone")
    suspend fun getAllClients(city:Int, zone:Int):List<ClientEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClients(clientsList:List<ClientEntity>)

    @Query("DELETE FROM clients_table")
    suspend fun clearClients()

    //lecturas
    @Query("SELECT * FROM lecturas_table WHERE client_id=:client_id and state = 'pendiente'")
    suspend fun getAllLecturas(client_id:Int):List<LecturaEntity>?

    @Query("SELECT * FROM lecturas_table WHERE client_id=:client_id ORDER BY id DESC LIMIT 1")
    suspend fun getLastLectura(client_id:Int):LecturaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecturas(LecturasList:List<LecturaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLectura(lectura:LecturaEntity)

    @Query("DELETE FROM lecturas_table")
    suspend fun clearLecturas()

    //periods
    @Query("SELECT * FROM periods_table ORDER BY paymentDate DESC LIMIT 1")
    suspend fun getPeriod():PeriodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeriod(period:PeriodEntity)

    @Query("DELETE FROM periods_table")
    suspend fun clearPeriods()

}