package com.jansellopez.eemjoy.ui.zones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        val city = bundle!!.getString("city")

        zonesViewModel.onCreate(city?:"")

        binding.rvZones.layoutManager = LinearLayoutManager(this)

        zonesViewModel.zones.observe(this,{
            binding.rvZones.adapter = ZoneAdapter(it,city?:"")
        })

    }

}