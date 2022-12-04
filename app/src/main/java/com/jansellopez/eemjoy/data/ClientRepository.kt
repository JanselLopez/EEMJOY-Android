package com.jansellopez.eemjoy.data

import android.content.Context
import android.util.Log
import com.jansellopez.eemjoy.data.database.dao.ClientDao
import com.jansellopez.eemjoy.data.database.entities.*
import com.jansellopez.eemjoy.data.model.*
import com.jansellopez.eemjoy.data.network.UserService
import com.jansellopez.eemjoy.data.network.modelnetwork.toDomain
import com.jansellopez.eemjoy.data.network.responses.ResponseAddLecturas
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: ClientDao
) {

    suspend fun login(user: User): Token = userService.login(user.toDomain()).toDomain()

    suspend fun getCitiesFromApi(context:Context):List<City> = userService.getCities(TokenRepository.getToken(),context).map { it.toDomain() }

    suspend fun getCitiesFromDataBase():List<City> = userDao.getAllCities()!!.map { it.toDomain() }

    suspend fun pushCitiesToDatabase(cities:List<CityEntity>) = userDao.insertCities(cities)

    suspend fun deleteAllCitiesFromDatabase() = userDao.clearCities()

    suspend fun getZones(context:Context):List<Zone> = userService.getZones(TokenRepository.getToken(),context).map { it.toDomain() }

    suspend fun getZonesFromDataBase():List<Zone> = userDao.getAllZones()!!.map { it.toDomain() }

    suspend fun pushZonesToDatabase(zones:List<ZoneEntity>) = userDao.insertZones(zones)

    suspend fun deleteAllZonesFromDatabase() = userDao.clearZones()

    //clients
    suspend fun getClientsFromApi(city: Int, zone: Int,context:Context):List<Client> = userService.getClients(TokenRepository.getToken(),city,zone,context).map { it.toDomain() }

    suspend fun getClientsFromDataBase(city:Int,zone:Int):List<Client> = userDao.getAllClients(city,zone)!!.map { it.toDomain() }

    suspend fun pushClientsToDatabase(clients:List<ClientEntity>) = userDao.insertClients(clients)

    suspend fun deleteAllClientsFromDatabase() = userDao.clearClients()

    suspend fun getClientsByNameOrCount(value: String):List<Client> = userDao.getClientsByNameOrCount(value)!!.map { it.toDomain() }

    suspend fun getClientsByNameOrCountExactly(value: String):List<Client> = userDao.getClientsByNameOrCountExactly(value)!!.map { it.toDomain() }
    //lectura
    suspend fun getLecturasFromApi(zone: Int,context:Context):List<Lectura> = userService.getLecturas(TokenRepository.getToken(),zone,context).map { it.toDomain() }

    suspend fun getLecturasFromDataBase(clientId:Int):List<Lectura> = userDao.getAllLecturas(clientId)!!.map { it.toDomain() }

    suspend fun getLastLecturaFromDataBase(clientId: Int):Lectura {
        return when(val lectura = userDao.getLastLectura(clientId)){
            null-> Lectura(-1,0,0,0,0,"",0,0,0,0,"",1,0)
            else -> lectura.toDomain()
        }
    }

    suspend fun pushLecturasToDatabase(Lecturas:List<LecturaEntity>) = userDao.insertLecturas(Lecturas)

    suspend fun pushLecturaToDatabase(lectura: LecturaEntity) = userDao.insertLectura(lectura)

    suspend fun deleteAllLecturasFromDatabase() = userDao.clearLecturas()

    suspend fun getPeriodFromApi(context:Context):Period {
        val period = userService.getPeriod(TokenRepository.getToken(),context)
        val calEnd: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        calEnd.time = sdf.parse(period.endDate)
        val calBegin: Calendar = Calendar.getInstance()
        calBegin.time = sdf.parse(period.beginDate)
        val calPay: Calendar = Calendar.getInstance()
        calPay.time = sdf.parse(period.paymentDate)
        Log.e("Fecha Periodo","Begin: ${calBegin.get(Calendar.DAY_OF_MONTH)}-${calBegin.get(Calendar.MONTH)} Final: ${calEnd.get(Calendar.DAY_OF_MONTH)}-${calEnd.get(Calendar.MONTH)}")
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

    suspend fun pushLecturaToApi(lecturaAdd: LecturaAdd,context: Context):ResponseAddLecturas = userService.pushLecturas(TokenRepository.getToken(),lecturaAdd.toDomain(),context)

    suspend fun pushLecturaForAddToDatabase(lecturaEntityAdd: LecturaEntityAdd) = userDao.insertLecturaAdd(lecturaEntityAdd)

    suspend fun clearLecturasAdd() = userDao.clearLecturasAdd()

    suspend fun getLecturasFromDataBase():List<LecturaAdd> = userDao.getAllLecturasForAdd()!!.map { it.toDomain() }

    //Tarifas
    suspend fun getTarifasFromApi(context:Context):List<Tarifa> = userService.getTarifas(TokenRepository.getToken(),context).map { it.toDomain() }

    suspend fun getTarifasFromDataBase():List<Tarifa> = userDao.getTarifas()!!.map { it.toDomain() }

    suspend fun pushTarifasToDatabase(tarifas:List<TarifasEntity>) = userDao.insertTarifas(tarifas)

    suspend fun deleteAllTarifasFromDatabase() = userDao.clearTarifas()

    //updates
    suspend fun updateLectura(lectura: Lectura) = userDao.updateLecturas(lectura.toDomain())

    suspend fun updateLecturaAdd(lecturaEntityAdd: LecturaEntityAdd) = userDao.updateLecturasAdd(lecturaEntityAdd)

    suspend fun getMaxIdForAdd() = userDao.getMaxIdOfLectForAdd()?:1

    //lecturas add
    suspend fun deleteLecturaAdd(id_add:Int) = userDao.deleteLecturaAdd(id_add)
}