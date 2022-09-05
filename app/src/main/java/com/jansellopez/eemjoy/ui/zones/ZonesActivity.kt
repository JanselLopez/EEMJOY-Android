package com.jansellopez.eemjoy.ui.zones

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.core.TokenRepository
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.databinding.ActivityZonesBinding
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

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        zonesViewModel.onCreate(city, TokenRepository.getToken(),connectivityManager)

        binding.rvZones.layoutManager = LinearLayoutManager(this)

        zonesViewModel.zones.observe(this,{
            binding.rvZones.adapter = ZoneAdapter(it,city?:1)
        })

    }

}