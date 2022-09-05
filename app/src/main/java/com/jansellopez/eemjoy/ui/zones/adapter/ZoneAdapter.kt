package com.jansellopez.eemjoy.ui.zones.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.databinding.CvZoneBinding
import com.jansellopez.eemjoy.ui.clients.ClientsActivity

class ZoneAdapter(
    private val zones: List<Zone>,
    private val city: Int
):RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder>() {

    private lateinit var context:Context

    inner class ZoneViewHolder(val binding: CvZoneBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneViewHolder {
        context = parent.context
        val binding= CvZoneBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ZoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZoneViewHolder, position: Int) {
        with(holder){
            with(zones[position]){
                binding.tvZone.text = this.address
                binding.cvZone.setOnClickListener {
                    val i = Intent(context,ClientsActivity::class.java)
                    i.putExtra("city",city)
                    i.putExtra("zone",this.id)
                    context.startActivity(i)
                }
            }
        }
    }

    override fun getItemCount(): Int = zones.size



}