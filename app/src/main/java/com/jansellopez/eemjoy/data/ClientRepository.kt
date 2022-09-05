package com.jansellopez.eemjoy.data

import android.util.Log
import com.jansellopez.eemjoy.data.database.dao.ClientDao
import com.jansellopez.eemjoy.data.database.entities.CityEntity
import com.jansellopez.eemjoy.data.database.entities.ZoneEntity
import com.jansellopez.eemjoy.data.model.*
import com.jansellopez.eemjoy.data.network.UserService
import com.jansellopez.eemjoy.data.network.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: ClientDao
) {

    suspend fun login(user: User): Token = userService.login(user.toDomain()).toDomain()

    suspend fun getCitiesFromApi(token: Token):List<City> = userService.getCities(token).map { it.toDomain() }

    suspend fun getCitiesFromDataBase():List<City> = userDao.getAllCities()!!.map { it.toDomain() }

    suspend fun pushCitiesToDatabase(cities:List<CityEntity>) = userDao.insertCities(cities)

    suspend fun deleteAllCitiesFromDatabase() = userDao.clearCities()

    suspend fun getZones(token: Token):List<Zone> = userService.getZones(token).map { it.toDomain() }

    suspend fun getZonesFromDataBase():List<Zone> = userDao.getAllZones()!!.map { it.toDomain() }

    suspend fun pushZonesToDatabase(cities:List<ZoneEntity>) = userDao.insertZones(cities)

    suspend fun deleteAllZonesFromDatabase() = userDao.clearZones()

    suspend fun getClients(token: Token):List<Client> = userService.getClients(token).map { it.toDomain() }

}