package com.jansellopez.eemjoy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.databinding.ActivityHomeBinding
import com.jansellopez.eemjoy.ui.home.adapter.CityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding:ActivityHomeBinding by lazy{
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras

        binding.tvUser.text = bundle!!.getString("email","")

        binding.rvCities.layoutManager = GridLayoutManager(this,2)

        val token = Token(bundle.getString("token",""),bundle.getString("type",""))

            homeViewModel.onCreate(token)

        homeViewModel.cities.observe(this,{
            binding.rvCities.adapter = CityAdapter(it,token)
        })
    }
}