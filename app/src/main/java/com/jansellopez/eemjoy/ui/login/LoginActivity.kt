package com.jansellopez.eemjoy.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jansellopez.eemjoy.databinding.ActivityLoginBinding
import com.jansellopez.eemjoy.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by lazy{ActivityLoginBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition{false}

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}