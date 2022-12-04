package com.jansellopez.eemjoy.ui.clients

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.core.CheckConnect
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.databinding.ActivityClientsBinding
import com.jansellopez.eemjoy.ui.clients.adapter.ClientAdapter
import com.jansellopez.eemjoy.ui.zones.ZonesActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ClientsActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var clientAdapter:ClientAdapter? = null

    private val binding:ActivityClientsBinding by lazy{ ActivityClientsBinding.inflate(layoutInflater) }

    private val clientsViewModel:ClientsViewModel by viewModels()

    private var city: Int? =null

    private var firstChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvClients.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras

        city = bundle!!.getInt("city")
        val zone = bundle.getInt("zone")
        val zoneName = bundle.getString("zone_name")

        binding.scExact.setOnCheckedChangeListener { _, _ ->
            onQueryTextChange(binding.svClient.query.toString())
        }
        clientsViewModel.onCreate(city!!, zone, CheckConnect(this),this)
        clientsViewModel.getClientsByNameOrCount("",false)
        clientsViewModel.users.observe(this) {
            clientsViewModel.lecturas.observe(this) { lecturas ->
                clientsViewModel.period.observe(this) { period ->
                    clientsViewModel.tarifas.observe(this) { tarifas ->
                           clientsViewModel.lastsLecturas.observe(this) { lasts ->
                               clientAdapter = ClientAdapter(
                                   if (!it.isNullOrEmpty()) it as MutableList<Client> else emptyList<Client>() as MutableList,
                                   zone,
                                   this,
                                   binding.coordinator,
                                   if (!it.isNullOrEmpty()) lecturas else emptyList(),
                                   zoneName ?: "",
                                   period,
                                   tarifas,
                                   city!!,
                                   lasts as MutableList<Lectura>
                               )
                               binding.rvClients.adapter = clientAdapter
                               firstChange = false
                           }
                    }
                }
            }
        }

        clientsViewModel.loading.observe(this) {
            binding.rvClients.isVisible = !it
            binding.shimmer.isVisible = it
            binding.svClient.isEnabled = !it
            binding.svClient.isVisible = !it
            requestedOrientation = if (it) {
                if (resources.configuration.orientation == ORIENTATION_LANDSCAPE)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else
                ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }

        binding.svClient.setOnQueryTextListener(this)
        binding.svClient.setOnClickListener { binding.svClient.isIconified = false}

        clientsViewModel.period.observe(this) {
            binding.appCompatTextView6.text = it.title
        }

        binding.toolbar.setNavigationOnClickListener {
            loadBack()
        }

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(!p0.isNullOrEmpty())
        clientsViewModel.getClientsByNameOrCount(p0,binding.scExact.isChecked)
        return true
    }


    override fun onQueryTextChange(p0: String?): Boolean {
       // clientAdapter?.filter(p0!!, binding.scExact.isChecked)
        if(!p0.isNullOrEmpty())
            clientsViewModel.getClientsByNameOrCount(p0,binding.scExact.isChecked)
        return false
    }

    override fun onBackPressed() {
        loadBack()
    }

    private fun loadBack(){
        Intent(this,ZonesActivity::class.java).apply {
            putExtra("city",city)
            startActivity(this)
        }
    }

}