package com.jansellopez.eemjoy.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.data.model.City
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.databinding.CvCityBinding
import com.jansellopez.eemjoy.databinding.CvZoneBinding
import com.jansellopez.eemjoy.ui.zones.ZonesActivity

class CityAdapter(
    private val zones:List<City>,
    private val token: Token
):RecyclerView.Adapter<CityAdapter.ZoneViewHolder>() {

    private lateinit var context: Context

    inner class ZoneViewHolder(val binding: CvCityBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneViewHolder {
        context = parent.context
        val binding = CvCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ZoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZoneViewHolder, position: Int) {
        with(holder){
            with(zones[position]){
                binding.tvZone.text = this.name
                binding.cvCity.setOnClickListener {
                    val i = Intent(context,ZonesActivity::class.java)
                    i.putExtra("city",this.id)
                    i.putExtra("type",token.token_type)
                    i.putExtra("token",token.access_token)
                    context.startActivity(i)
                }
            }
        }
    }

    override fun getItemCount(): Int = zones.size

}