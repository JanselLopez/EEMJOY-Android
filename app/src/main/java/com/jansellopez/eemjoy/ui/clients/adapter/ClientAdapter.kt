package com.jansellopez.eemjoy.ui.clients.adapter


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.core.PrintTarifa
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.data.model.Tarifa
import com.jansellopez.eemjoy.databinding.BottomsheetClientBinding
import com.jansellopez.eemjoy.databinding.CvClienteBinding
import com.jansellopez.eemjoy.ui.lecturas.LecturasActivity
import java.util.*
import java.util.stream.Collectors


class ClientAdapter(
    private var users: MutableList<Client>,
    private val zone: Int,
    private val activity: Activity,
    private val coordinatorLayout: CoordinatorLayout,
    private val lecturas: List<Lectura>,
    private val zoneName:String,
    private val periodFull: Period,
    private val tarifas: List<Tarifa>,
    private val city:Int,
    private val lastsLecturas: MutableList<Lectura>,
):RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {


    private var all = mutableListOf<Client>()

    private lateinit var context:Context

    init {
        users.sort()
        lastsLecturas.sort()
        all.addAll(users)

    }

    inner class ClientViewHolder(val binding: CvClienteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        context = parent.context
        val binding = CvClienteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientViewHolder(binding)
    }

    fun filter(str:String, exact:Boolean){

                if (str.isEmpty()) {
                    users.clear()
                    users.addAll(all)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        val collection =
                            users.stream()
                                .filter { i ->
                                    if(!exact){
                                    ((i.numberCount.lowercase(Locale.getDefault()).contains(
                                        str.lowercase(
                                            Locale.getDefault()
                                        )
                                    )) || ((i.firstName + i.firstLastName + i.secondLastName).lowercase(
                                        Locale.getDefault()
                                    ).contains(str.lowercase(Locale.getDefault()))))}
                                    else{
                                        ((i.numberCount.lowercase(Locale.getDefault())== str.lowercase(Locale.getDefault())
                                        ) || ((i.firstName + i.firstLastName + i.secondLastName).lowercase(
                                            Locale.getDefault()
                                        )==(str.lowercase(Locale.getDefault()))))
                                    }
                                }
                                .collect(Collectors.toList())
                        users.clear()
                        users.addAll(collection)
                    } else {
                        users.clear()
                        for (r in all) {
                            if(!exact) {
                                if (((r.numberCount.lowercase(Locale.getDefault()).contains(
                                        str.lowercase(
                                            Locale.getDefault()
                                        )
                                    )) || ((r.firstName + r.firstLastName + r.secondLastName).lowercase(
                                        Locale.getDefault()
                                    ).contains(str.lowercase(Locale.getDefault()))))
                                )
                                    users.add(r)
                            }else{
                                if (((r.numberCount.lowercase(Locale.getDefault())==(
                                        str.lowercase(
                                            Locale.getDefault()
                                        )
                                    )) || ((r.firstName + r.firstLastName + r.secondLastName).lowercase(
                                        Locale.getDefault()
                                    )==(str.lowercase(Locale.getDefault()))))
                                )
                                    users.add(r)
                            }
                        }
                    }
                }
                users.sort()
                notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                binding.tvContador.text = numberCount
                binding.tvName.text = "$firstName $firstLastName $secondLastName"
                val lecturasCurrentClient = lecturas.filter { it.client_id == id }
                val lastLecturaIndex:Int = lastsLecturas.binarySearch { if(it.client_id > id) 1 else if(it.client_id < id) -1 else 0 }
                val cantLecturas = lecturasCurrentClient.size
                binding.cvNoti.isVisible = cantLecturas>0
                binding.tvNoti.isVisible = cantLecturas>0
                binding.tvNoti.text = cantLecturas.toString()

                try {
                    if (lastLecturaIndex != -1 && lastsLecturas[lastLecturaIndex].configuracion_id != periodFull.id)
                        ViewCompat.setBackgroundTintList(
                            binding.btnGo,
                            context.resources.getColorStateList(R.color.dark_blue)
                        )
                    else
                        ViewCompat.setBackgroundTintList(
                            binding.btnGo,
                            context.resources.getColorStateList(R.color.colorAccent)
                        )
                }catch (e:Exception){
                    ViewCompat.setBackgroundTintList(
                        binding.btnGo,
                        context.resources.getColorStateList(R.color.colorAccent)
                    )
                }
                if(lastLecturaIndex!=-1 && cantLecturas>0 && lastsLecturas[lastLecturaIndex].configuracion_id == periodFull.id && lastsLecturas[lastLecturaIndex].id_add !=null)
                    binding.cvNoti.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                else
                    binding.cvNoti.setCardBackgroundColor(ContextCompat.getColor(context, R.color.other_red))
                binding.btnGo.setOnClickListener {
                    val bottomSheetDialog = BottomSheetDialog(context,R.style.BottomSheetDialogTheme)
                    val bindingBS = BottomsheetClientBinding.inflate(LayoutInflater.from(context))
                    bindingBS.tvContador.text = numberCount
                    bindingBS.tvName.text = "$firstName $firstLastName"
                    bindingBS.btnLecturas.setOnClickListener {
                        Intent(context,LecturasActivity::class.java).apply {
                            putExtra("counter",numberCount)
                            putExtra("name", binding.tvName.text)
                            putExtra("zone",zone)
                            putExtra("clientId",id)
                            putExtra("city",city)
                            putExtra("zone_name",zoneName)
                            putExtra("firstName",firstName)
                            putExtra("firstLastName",firstLastName)
                            putExtra("secondLastName",secondLastName)

                            putExtra("pf_id",periodFull.id)
                            putExtra("pf_title",periodFull.title)
                            putExtra("pf_beginDate",periodFull.beginDate.timeInMillis)
                            putExtra("pf_endDate",periodFull.endDate.timeInMillis)
                            putExtra("pf_paymentDate",periodFull.paymentDate.timeInMillis)


                            context.startActivity(this)
                        }
                    }

                   bindingBS.printAccount.setOnClickListener {
                       PrintTarifa(context,activity,this,zoneName,periodFull, lecturas, tarifas, coordinatorLayout).print()
                    }
                    bottomSheetDialog.apply {
                        setContentView(bindingBS.root)
                        show()
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int = users.size

}