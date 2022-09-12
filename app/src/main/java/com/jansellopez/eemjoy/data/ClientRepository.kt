package com.jansellopez.eemjoy.data

import com.jansellopez.eemjoy.data.database.dao.ClientDao
import com.jansellopez.eemjoy.data.database.entities.*
import com.jansellopez.eemjoy.data.model.*
import com.jansellopez.eemjoy.data.network.UserService
import com.jansellopez.eemjoy.data.network.modelnetwork.toDomain
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ClientRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: ClientDao
) {

    suspend fun login(user: User): Token = userService.login(user.toDomain()).toDomain()

    suspend fun getCitiesFromApi():List<City> = userService.getCities(TokenRepository.getToken()).map { it.toDomain() }

    suspend fun getCitiesFromDataBase():List<City> = userDao.getAllCities()!!.map { it.toDomain() }

    suspend fun pushCitiesToDatabase(cities:List<CityEntity>) = userDao.insertCities(cities)

    suspend fun deleteAllCitiesFromDatabase() = userDao.clearCities()

    suspend fun getZones():List<Zone> = userService.getZones(TokenRepository.getToken()).map { it.toDomain() }

    suspend fun getZonesFromDataBase():List<Zone> = userDao.getAllZones()!!.map { it.toDomain() }

    suspend fun pushZonesToDatabase(zones:List<ZoneEntity>) = userDao.insertZones(zones)

    suspend fun deleteAllZonesFromDatabase() = userDao.clearZones()

    suspend fun getClientsFromApi(city: Int, zone: Int):List<Client> = userService.getClients(TokenRepository.getToken(),city,zone).map { it.toDomain() }

    suspend fun getClientsFromDataBase(city:Int,zone:Int):List<Client> = userDao.getAllClients(city,zone)!!.map { it.toDomain() }

    suspend fun pushClientsToDatabase(clients:List<ClientEntity>) = userDao.insertClients(clients)

    suspend fun deleteAllClientsFromDatabase() = userDao.clearClients()

    suspend fun getLecturasFromApi(zone: Int):List<Lectura> = userService.getLecturas(TokenRepository.getToken(),zone).map { it.toDomain() }

    suspend fun getLecturasFromDataBase(clientId:Int):List<Lectura> = userDao.getAllLecturas(clientId)!!.map { it.toDomain() }

    suspend fun getLastLecturaFromDataBase(clientId: Int):Lectura = userDao.getLastLectura(clientId)!!.toDomain()

    suspend fun pushLecturasToDatabase(Lecturas:List<LecturaEntity>) = userDao.insertLecturas(Lecturas)

    suspend fun pushLecturaToDatabase(lectura: LecturaEntity) = userDao.insertLectura(lectura)

    suspend fun deleteAllLecturasFromDatabase() = userDao.clearLecturas()

    suspend fun getPeriodFromApi():Period {
        val period = userService.getPeriod(TokenRepository.getToken())
        val calEnd: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        calEnd.time = sdf.parse(period.endDate)
        val calBegin: Calendar = Calendar.getInstance()
        calBegin.time = sdf.parse(period.beginDate)
        val calPay: Calendar = Calendar.getInstance()
        calPay.time = sdf.parse(period.paymentDate)
        return period.toDomain(endDateC = calEnd,beginDateC = calBegin,paymentDateC = calPay)
    }

    suspend fun getPeriodsFromDataBase():Period {
        val period = userDao.getPeriod()!!
        val calPay: Calendar = Calendar.getInstance()
        calPay.timeInMillis = period.paymentDate
        val calBegin: Calendar = Calendar.getInstance()
        calBegin.timeInMillis = period.beginDate
        val calEnd: Calendar = Calendar.getInstance()
        calEnd.timeInMillis = period.endDate
       return period.toDomain(beginDateC = calBegin,endDateC = calEnd,paymentDateC = calPay)
    }

    suspend fun pushPeriodsToDatabase(period:PeriodEntity) = userDao.insertPeriod(period)

    suspend fun deleteAllPeriodsFromDatabase() = userDao.clearPeriods()

}