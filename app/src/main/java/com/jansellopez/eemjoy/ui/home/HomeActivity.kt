package com.jansellopez.eemjoy.ui.home

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.TokenRepository
import com.jansellopez.eemjoy.databinding.ActivityHomeBinding
import com.jansellopez.eemjoy.ui.home.adapter.CityAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import com.jansellopez.eemjoy.ui.login.LoginActivity
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import cu.jansellopez.custom_snackbars.CustomSnackBar
import java.util.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding:ActivityHomeBinding by lazy{
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel:HomeViewModel by viewModels()
    private lateinit var customSnackBar:CustomSnackBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        customSnackBar = CustomSnackBar(this,binding.coordinator)

        binding.tvUser.text = SharedPreferenceManager.getINstance(this).token.username

        binding.rvCities.layoutManager = GridLayoutManager(this,2)

        setSupportActionBar(binding.toolbar)

        homeViewModel.onCreate(CheckConnect(this),this)

        homeViewModel.cities.observe(this,{
            binding.rvCities.adapter = CityAdapter(it, TokenRepository.getToken())
        })

        homeViewModel.loading.observe(this,{
            binding.rvCities.isVisible = !it
            binding.shimmer.isVisible =it
            requestedOrientation = if(it){
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            else
                ActivityInfo.SCREEN_ORIENTATION_SENSOR
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.log_out -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle(resources.getString(R.string.log_out_application))
                builder.setPositiveButton(android.R.string.ok
                ) { _, _ ->
                    SharedPreferenceManager.getINstance(this@HomeActivity).clear()
                    startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
                }
                builder.setNegativeButton(android.R.string.cancel
                ) { dialog, _ ->
                    dialog.cancel() }
                builder.show()
            }
        }
        return true
    }

    override fun onBackPressed() {
        customSnackBar.onBackPressedToExit()
    }

}