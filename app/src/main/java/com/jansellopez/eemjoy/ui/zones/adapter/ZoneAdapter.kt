package com.jansellopez.eemjoy.ui.zones.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Zone
import com.jansellopez.eemjoy.databinding.CvZoneBinding
import com.jansellopez.eemjoy.ui.clients.ClientsActivity
import java.util.*
import java.util.stream.Collectors

class ZoneAdapter(
    private val zones: MutableList<Zone>,
    private val city: Int
):RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder>() {
    private var all = mutableListOf<Zone>()
    private lateinit var context:Context

    init {
        all.addAll(zones)
    }

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
                    i.putExtra("zone_name",this.address)
                    context.startActivity(i)
                }
            }
        }
    }

    override fun getItemCount(): Int = zones.size

    fun filter(str:String){

        if (str.isEmpty()) {
            zones.clear()
            zones.addAll(all)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val collection =
                    zones.stream()
                        .filter { i ->
                            ((i.address.lowercase(Locale.getDefault()).contains(
                                str.lowercase(
                                    Locale.getDefault()
                                )
                            )))
                        }
                        .collect(Collectors.toList())
                zones.clear()
                zones.addAll(collection)
            } else {
                zones.clear()
                for (r in all) {
                    if (((r.address.lowercase(Locale.getDefault()).contains(
                            str.lowercase(
                                Locale.getDefault()
                            )
                        )))
                    )
                        zones.add(r)
                }
            }
        }
        notifyDataSetChanged()

    }

}