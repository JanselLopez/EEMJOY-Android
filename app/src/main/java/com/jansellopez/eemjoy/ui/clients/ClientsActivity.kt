package com.jansellopez.eemjoy.ui.clients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.databinding.ActivityClientsBinding
import com.jansellopez.eemjoy.ui.clients.adapter.ClientAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsActivity : AppCompatActivity() {

    private val binding:ActivityClientsBinding by lazy{ ActivityClientsBinding.inflate(layoutInflater) }

    private val clientsViewModel:ClientsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvClients.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras

        val city = bundle!!.getString("city")
        val zone = bundle.getString("zone")

        if (city != null && zone != null) {
                clientsViewModel.onCreate(city,zone)
        }

        clientsViewModel.users.observe(this,{
            binding.rvClients.adapter = ClientAdapter(it)
        })

    }
}