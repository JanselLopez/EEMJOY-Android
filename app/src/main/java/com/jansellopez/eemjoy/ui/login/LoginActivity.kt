package com.jansellopez.eemjoy.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
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
        }

        loginViewModel.token.observe(this,{
            Log.e("token ",it.access_token)
            if (!it.access_token.isNullOrEmpty()){
                SharedPreferenceManager.getINstance(this).saveUser(User(binding.tvEmail.text.toString(), binding.tvPassword.text.toString()))
                SharedPreferenceManager.getINstance(this).saveToken(it)
                val intent = Intent(this,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("email",binding.tvEmail.text.toString())
                intent.putExtra("token",it.access_token)
                intent.putExtra("type",it.token_type)
                startActivity(intent)
            }
        })
    }


}