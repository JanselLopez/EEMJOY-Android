package com.jansellopez.eemjoy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.databinding.CvZoneBinding

class ZoneAdapter(
    private val zones:List<String>
):RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder>() {
    inner class ZoneViewHolder(val binding: CvZoneBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneViewHolder {
        val binding = CvZoneBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ZoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZoneViewHolder, position: Int) {
        with(holder){
            with(zones[position]){
                binding.tvZone.text = this
            }
        }
    }

    override fun getItemCount(): Int = zones.size

}