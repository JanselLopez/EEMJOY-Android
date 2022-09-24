package com.jansellopez.eemjoy.ui.clients

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.databinding.ActivityClientsBinding
import com.jansellopez.eemjoy.ui.clients.adapter.ClientAdapter
import com.jansellopez.eemjoy.ui.lecturas.LecturaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var clientAdapter:ClientAdapter? = null

    private val binding:ActivityClientsBinding by lazy{ ActivityClientsBinding.inflate(layoutInflater) }

    private val clientsViewModel:ClientsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvClients.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras

        val city = bundle!!.getInt("city")
        val zone = bundle.getInt("zone")
        val zoneName = bundle.getString("zone_name")

        clientsViewModel.onCreate(city, zone, CheckConnect(this),this)

        clientsViewModel.users.observe(this,{
            clientsViewModel.lecturas.observe(this,{ lecturas ->
                clientsViewModel.period.observe(this,{ period->
                    clientsViewModel.tarifas.observe(this,{ tarifas ->
                            clientAdapter = ClientAdapter(
                                if(!it.isNullOrEmpty()) it as MutableList<Client> else emptyList<Client>() as MutableList,
                                zone,
                                this,
                                binding.coordinator,
                                if(!it.isNullOrEmpty()) lecturas else emptyList(),
                                zoneName ?: "",
                                period,
                                tarifas,
                                city
                            )
                        binding.rvClients.adapter = clientAdapter
                    })
                })
            })
        })

        clientsViewModel.loading.observe(this,{
            binding.rvClients.isVisible = !it
            binding.shimmer.isVisible =it
            binding.svClient.isEnabled = !it
            binding.svClient.isVisible = !it
            requestedOrientation = if(it){
                if(resources.configuration.orientation == ORIENTATION_LANDSCAPE)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            else
                ActivityInfo.SCREEN_ORIENTATION_SENSOR
        })

        binding.svClient.setOnQueryTextListener(this)

        clientsViewModel.period.observe(this,{
            binding.appCompatTextView6.text = it.title
        })

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onQueryTextSubmit(p0: String?): Boolean = true


    override fun onQueryTextChange(p0: String?): Boolean {
        clientAdapter?.filter(p0!!)

        return false
    }

}