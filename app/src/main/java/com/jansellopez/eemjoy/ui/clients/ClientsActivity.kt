package com.jansellopez.eemjoy.ui.clients

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

    private lateinit var clientAdapter:ClientAdapter

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
                            it as MutableList<Client>,
                            zone,
                            this,
                            binding.coordinator,
                            lecturas,
                            zoneName?:"",
                            period,
                            tarifas
                        )
                        binding.rvClients.adapter = clientAdapter
                    })
                })
            })
        })

        clientsViewModel.loading.observe(this,{
            binding.rvClients.isVisible = !it
            binding.shimmer.isVisible =it
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
        clientAdapter.filter(p0!!)
        return false
    }


}