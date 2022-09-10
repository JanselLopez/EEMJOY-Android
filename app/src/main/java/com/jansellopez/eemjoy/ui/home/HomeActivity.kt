package com.jansellopez.eemjoy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.jansellopez.eemjoy.data.TokenRepository
import com.jansellopez.eemjoy.databinding.ActivityHomeBinding
import com.jansellopez.eemjoy.ui.home.adapter.CityAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.jansellopez.eemjoy.core.CheckConnect


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



        homeViewModel.onCreate(CheckConnect(this))

        homeViewModel.cities.observe(this,{
            binding.rvCities.adapter = CityAdapter(it, TokenRepository.getToken())
        })

        homeViewModel.loading.observe(this,{
            binding.rvCities.isVisible = !it
            binding.shimmer.isVisible =it
        })
    }

}