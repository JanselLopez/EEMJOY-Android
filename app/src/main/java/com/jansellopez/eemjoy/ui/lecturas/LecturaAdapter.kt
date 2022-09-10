package com.jansellopez.eemjoy.ui.lecturas

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.databinding.CvLecturaBinding
import java.util.*

class LecturaAdapter(
    private val lecturas: List<Lectura>
):RecyclerView.Adapter<LecturaAdapter.LecturaViewHolder>() {

    private lateinit var context: Context

    inner class LecturaViewHolder(val binding:CvLecturaBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturaViewHolder {
        context = parent.context
        val binding = CvLecturaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LecturaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LecturaViewHolder, position: Int) {
        with(holder){
            with(lecturas[position]){
                binding.tvDate.text = lectura_actual.toString()
                val kb = "$kilovatios ${context.resources.getString(R.string.kwh)}"
                binding.tvKw.text = kb
                binding.tvState.text = state
                if (this.state == context.resources.getString(R.string.pendiente))
                    binding.tvState.setTextColor(context.resources.getColor(R.color.red))
                else
                    binding.tvState.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
            }
        }
    }

    override fun getItemCount(): Int = lecturas.size


}