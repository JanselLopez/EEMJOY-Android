package com.jansellopez.eemjoy.ui.clients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.databinding.ActivityClientsBinding
import com.jansellopez.eemjoy.ui.clients.adapter.ClientAdapter
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

        val city = bundle!!.getString("city")
        val zone = bundle.getString("zone")

        if (city != null && zone != null) {
                clientsViewModel.onCreate(city,zone)
        }

        clientsViewModel.users.observe(this,{
            clientAdapter = ClientAdapter(it as MutableList<Client>)
            binding.rvClients.adapter = clientAdapter
        })

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        clientAdapter.filter(p0!!)
        return false
    }
}