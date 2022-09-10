package com.jansellopez.eemjoy.core

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

object CheckConnect {
    operator fun invoke(context:Context): Boolean {
        val connectivityManager = context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val actNetInfo = connectivityManager.activeNetworkInfo
        return actNetInfo != null && actNetInfo.isConnected
    }
}