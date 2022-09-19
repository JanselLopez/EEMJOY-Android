package com.jansellopez.eemjoy.ui.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.TokenRepository
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import com.jansellopez.eemjoy.databinding.ActivityLoginBinding
import com.jansellopez.eemjoy.ui.home.HomeActivity
import cu.jansellopez.custom_snackbars.CustomSnackBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by lazy{ActivityLoginBinding.inflate(layoutInflater)}

    private val loginViewModel:LoginViewModel by viewModels()

    private lateinit var customSnackBar:CustomSnackBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        customSnackBar = CustomSnackBar(this,binding.coordinator)

        val userPreference = SharedPreferenceManager.getINstance(this).user

        if(userPreference.email.isNotEmpty()){
            if(CheckConnect(this)) {
                binding.tvEmail.setText(userPreference.email)
                binding.tvPassword.setText(userPreference.password)
                loginViewModel.login(userPreference,this, CoordinatorLayout(this), CheckConnect(this))
            }else{
                val intent = Intent(this,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("email",userPreference.email)
                startActivity(intent)
                finish()
            }
        }

        binding.btnLogin.setOnClickListener {
            if(binding.tvEmail.text.toString().isNotEmpty() && binding.tvPassword.text.toString().isNotEmpty()) {
                try {
                    loginViewModel.login(
                        User(
                            binding.tvEmail.text.toString(),
                            binding.tvPassword.text.toString(),
                        ),
                        this,
                        binding.coordinator,
                        CheckConnect(this)
                    )
                }catch (e:Exception){
                    Log.e("Error","Serve")
                }
            }
        }


        loginViewModel.token.observe(this,{
            Log.e("token ",it.access_token)
            if (it.access_token.isNotEmpty()){
                SharedPreferenceManager.getINstance(this).saveUser(User(binding.tvEmail.text.toString(), binding.tvPassword.text.toString()))
                SharedPreferenceManager.getINstance(this).saveToken(it)
                val intent = Intent(this,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("email",binding.tvEmail.text.toString())
                TokenRepository.setToken(it)
                startActivity(intent)
            }
        })

        loginViewModel.loading.observe(this,{
            binding.tvEmail.isEnabled = !it
            binding.tvPassword.isEnabled = !it
            binding.btnLogin.isVisible = !it
            binding.progressCircular.isVisible =it
        })
    }

    override fun onBackPressed() {
        customSnackBar.onBackPressed()
    }


}