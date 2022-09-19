package com.jansellopez.eemjoy.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import com.jansellopez.eemjoy.ui.home.HomeActivity
import com.jansellopez.eemjoy.ui.login.LoginActivity
import com.jansellopez.eemjoy.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
    }
}