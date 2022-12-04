package com.jansellopez.eemjoy.data.model

import androidx.core.text.isDigitsOnly
import com.jansellopez.eemjoy.data.database.entities.ClientEntity
import com.jansellopez.eemjoy.data.network.modelnetwork.ClientNetwork

data class Client(
    val id:Int,
    val firstName:String?,
    val firstLastName: String?,
    val secondLastName:String?,
    val numberCount:String
    ) :Comparable<Client> {
    override fun compareTo(other: Client): Int {
            val n1 = (other.numberCount.filterNot { !it.isDigit() }).toInt()
            val n2 = (numberCount.filterNot { !it.isDigit() }).toInt()
            return if(n1<n2) 1 else if(n2<n1) -1 else 0
    }
}

fun ClientNetwork.toDomain() = Client(id = id,firstName= first_name?:"",firstLastName = first_lastname,secondLastName = second_lastname?:"", numberCount= number_cont)
fun ClientEntity.toDomain() = Client(id,firstName,firstLastName,secondLastName, numberCount)