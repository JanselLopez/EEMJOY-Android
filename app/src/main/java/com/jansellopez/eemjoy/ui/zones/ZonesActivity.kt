package com.jansellopez.eemjoy.ui.zones

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.databinding.ActivityZonesBinding
import com.jansellopez.eemjoy.ui.clients.adapter.ClientAdapter
import com.jansellopez.eemjoy.ui.home.HomeActivity
import com.jansellopez.eemjoy.ui.zones.adapter.ZoneAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZonesActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var zoneAdapter: ZoneAdapter? = null

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

        zonesViewModel.zones.observe(this) {
            zoneAdapter = ZoneAdapter(it as MutableList<Zone>, city)
            binding.rvZones.adapter = zoneAdapter
        }

        zonesViewModel.loading.observe(this) {
            binding.rvZones.isVisible = !it
            binding.shimmer.isVisible = it
            requestedOrientation = if (it) {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else
                ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        binding.svClient.setOnQueryTextListener(this)
        binding.cvSearch.setOnClickListener { binding.svClient.isIconified = false}

    }

    override fun onBackPressed() {
        startActivity(Intent(this,HomeActivity::class.java))
    }

    override fun onQueryTextSubmit(p0: String?): Boolean = true


    override fun onQueryTextChange(p0: String?): Boolean {
        zoneAdapter?.filter(p0!!)

        return false
    }



}