package com.jansellopez.eemjoy.ui.zones

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.databinding.ActivityZonesBinding
import com.jansellopez.eemjoy.ui.home.HomeActivity
import com.jansellopez.eemjoy.ui.zones.adapter.ZoneAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZonesActivity : AppCompatActivity() {

    private val binding:ActivityZonesBinding by lazy{
        ActivityZonesBinding.inflate(layoutInflater)
    }

    private val zonesViewModel:ZonesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        val city = bundle!!.getInt("city")

        zonesViewModel.onCreate(city, CheckConnect(this),this)

        binding.rvZones.layoutManager = LinearLayoutManager(this)

        zonesViewModel.zones.observe(this,{
            binding.rvZones.adapter = ZoneAdapter(it, city)
        })

        zonesViewModel.loading.observe(this,{
            binding.rvZones.isVisible = !it
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

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this,HomeActivity::class.java))
    }



}