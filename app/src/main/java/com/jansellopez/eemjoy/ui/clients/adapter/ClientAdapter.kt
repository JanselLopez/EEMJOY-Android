package com.jansellopez.eemjoy.ui.clients.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.User
import com.jansellopez.eemjoy.databinding.BottomsheetClientBinding
import com.jansellopez.eemjoy.databinding.CvClienteBinding
import com.jansellopez.eemjoy.ui.lecturas.LecturasActivity
import java.util.*
import java.util.stream.Collectors

class ClientAdapter(
    private val users:MutableList<Client>
):RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {
    private var all = mutableListOf<Client>()

    private lateinit var context:Context

    init {
        all.addAll(users)
    }

    inner class ClientViewHolder(val binding: CvClienteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        context = parent.context
        val binding = CvClienteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientViewHolder(binding)
    }

    fun filter(str:String){
        if(str.isEmpty()) {
            users.clear()
            users.addAll(all)
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val collection =
                    users.stream()
                        .filter { i -> i.numberCount.toString().toLowerCase().contains(str.lowercase(
                            Locale.getDefault()
                        )) }
                        .collect(Collectors.toList())
                users.clear()
                users.addAll(collection)
            } else {
                users.clear()
                for(r in all){
                    if(r.numberCount.toString().lowercase(Locale.getDefault()).contains(str.lowercase(Locale.getDefault())))
                        users.add(r)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                binding.tvContador.text = numberCount.toString()
                binding.tvName.text = "$firstName $firstLastName $secondLastName"
                binding.btnGo.setOnClickListener {
                    val bindingBS = BottomsheetClientBinding.inflate(LayoutInflater.from(context))
                    bindingBS.tvContador.text = numberCount.toString()
                    bindingBS.tvName.text = "$firstName $firstLastName"
                    bindingBS.btnLecturas.setOnClickListener {

                        Intent(context,LecturasActivity::class.java).apply {
                            putExtra("counter",numberCount)
                            putExtra("name", binding.tvName.text)
                            context.startActivity(this)
                        }

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