package com.jansellopez.eemjoy.data.model

import com.jansellopez.eemjoy.data.database.entities.ClientEntity
import com.jansellopez.eemjoy.data.database.entities.PeriodEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.PeriodNetwork
import com.jansellopez.eemjoy.data.network.modelnetwork.TokenNetwork
import java.util.*

data class Period(
    val id:Int,
    val title:String,
    val beginDate:Calendar,
    val endDate:Calendar,
    val paymentDate:Calendar
)
fun PeriodNetwork.toDomain(beginDateC:Calendar, endDateC: Calendar, paymentDateC: Calendar) = Period(id,title,beginDateC,endDateC,paymentDateC)
fun PeriodEntity.toDomain(beginDateC:Calendar, endDateC: Calendar, paymentDateC: Calendar) = Period(id,title,beginDateC,endDateC,paymentDateC)