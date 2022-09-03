package com.jansellopez.eemjoy.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.databinding.ActivityLoginBinding
import com.jansellopez.eemjoy.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by lazy{ActivityLoginBinding.inflate(layoutInflater)}

    private val loginViewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition{false}

        binding.btnLogin.setOnClickListener {
            if(!binding.tvEmail.text.toString().isNullOrEmpty() && !binding.tvPassword.text.toString().isNullOrEmpty())
                loginViewModel.login(User(binding.tvEmail.text.toString(),binding.tvPassword.text.toString()))
            /*Intent(this,HomeActivity::class.java).apply {
                putExtra("token","15|d0wQAJZunhf3U2Q88WKTEpCrFtjhpvQTYsqeS0VN")
            }*/
        }

        loginViewModel.token.observe(this,{
            if(!it.access_token.isNullOrEmpty()){
                startActivity(Intent(this, HomeActivity::class.java))
            }
        })


    }
}