package com.jansellopez.eemjoy.data.network.modelnetwork

import com.google.gson.annotations.SerializedName
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.data.model.User
import java.util.*

data class PeriodNetwork(
    @SerializedName("id")val id:Int,
    @SerializedName("titulo")val title:String,
    @SerializedName("fecha_inicio")val beginDate: String,
    @SerializedName("fecha_fin")val endDate: String,
    @SerializedName("fecha_pago")val paymentDate: String
)

fun Period.toDomain() = PeriodNetwork(id,title,beginDate.toString(),endDate.toString(),paymentDate.toString())