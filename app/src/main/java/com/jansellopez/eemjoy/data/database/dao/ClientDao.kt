package com.jansellopez.eemjoy.data.database.dao

import androidx.room.*
import com.jansellopez.eemjoy.data.database.entities.*
import com.jansellopez.eemjoy.data.model.LecturaAdd

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

    @Query("SELECT * FROM clients_table WHERE firstName LIKE '%' || :value || '%' or firstLastName LIKE '%' || :value || '%' or secondLastName LIKE '%' || :value || '%' or numberCount LIKE '%' || :value || '%' order by numberCount_integer limit 100")
    suspend fun getClientsByNameOrCount(value:String):List<ClientEntity>?

    @Query("SELECT * FROM clients_table WHERE firstName LIKE :value or firstLastName LIKE :value or secondLastName LIKE :value or numberCount LIKE :value")
    suspend fun getClientsByNameOrCountExactly(value:String):List<ClientEntity>?

    //lecturas
    @Query("SELECT * FROM lecturas_table WHERE client_id=:client_id and state = 'pendiente'")
    suspend fun getAllLecturas(client_id:Int):List<LecturaEntity>?

    @Query("SELECT * FROM lecturas_table WHERE client_id=:client_id and state <> 'sin pago' ORDER BY id DESC LIMIT 1")
    suspend fun getLastLectura(client_id:Int):LecturaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecturas(LecturasList:List<LecturaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLectura(lectura:LecturaEntity)

    @Query("DELETE FROM lecturas_table")
    suspend fun clearLecturas()

    @Update
    suspend fun updateLecturas(lecturaEntity: LecturaEntity)

    @Query("DELETE FROM lecturas_table WHERE id_add=:id_add")
    suspend fun deleteLecturaAdd(id_add:Int)

    //lecturas for add
    @Query("SELECT * FROM lecturas_add_table")
    suspend fun getAllLecturasForAdd():List<LecturaEntityAdd>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecturaAdd(lecturaEntityAdd: LecturaEntityAdd)

    @Query("DELETE FROM lecturas_add_table")
    suspend fun clearLecturasAdd()

    @Query("SELECT max(id) FROM lecturas_add_table")
    suspend fun getMaxIdOfLectForAdd():Int?

    @Update
    suspend fun updateLecturasAdd(lecturaEntityAdd: LecturaEntityAdd)

    //periods
    @Query("SELECT * FROM periods_table ORDER BY paymentDate DESC LIMIT 1")
    suspend fun getPeriod():PeriodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeriod(period:PeriodEntity)

    @Query("DELETE FROM periods_table")
    suspend fun clearPeriods()

    //tarifas
    @Query("SELECT * FROM tarifas_table")
    suspend fun getTarifas():List<TarifasEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarifas(tarifas:List<TarifasEntity>)

    @Query("DELETE FROM tarifas_table")
    suspend fun clearTarifas()



}