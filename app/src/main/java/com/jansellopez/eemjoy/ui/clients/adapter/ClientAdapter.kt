package com.jansellopez.eemjoy.ui.clients.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.databinding.BottomsheetClientBinding
import com.jansellopez.eemjoy.databinding.CvClienteBinding
import com.jansellopez.eemjoy.ui.lecturas.LecturasActivity

class ClientAdapter(
    private val users:List<Client>
):RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private lateinit var context:Context

    inner class ClientViewHolder(val binding: CvClienteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        context = parent.context
        val binding = CvClienteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                binding.tvContador.text = counter.toString()
                binding.tvName.text = "$firstName $firstLastName $secondLastName"
                binding.btnGo.setOnClickListener {
                    val bindingBS = BottomsheetClientBinding.inflate(LayoutInflater.from(context))
                    bindingBS.tvContador.text = counter.toString()
                    bindingBS.tvName.text = "$firstName $firstLastName"
                    bindingBS.btnLecturas.setOnClickListener {
                        context.startActivity(Intent(context,LecturasActivity::class.java))
                    }
                    BottomSheetDialog(context,R.style.BottomSheetDialogTheme).apply {
                        setContentView(bindingBS.root)
                        show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = users.size
}