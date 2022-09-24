package com.jansellopez.eemjoy.ui.lecturas

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.databinding.ActivityLecturasBinding
import com.jansellopez.eemjoy.databinding.CvLecturaBinding
import cu.jansellopez.custom_snackbars.CustomSnackBar
import java.util.*

class LecturaAdapter(
    private val lecturas: List<Lectura>,
    private val lecturaViewModel: LecturaViewModel,
    private val activity:Activity,
    private val counter:String
):RecyclerView.Adapter<LecturaAdapter.LecturaViewHolder>() {

    private lateinit var context: Context
    private var lectura = ""

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
                if (agregada == 1)
                    binding.fabEdit.isEnabled = false

                binding.fabEdit.setOnClickListener {
                        if (position != 0)
                            getLectura(this, lecturas[position- 1].lectura_actual,position)
                        else
                            getLectura(this, 0,position)
                }
            }
        }
    }

    override fun getItemCount(): Int = lecturas.size

    private fun getLectura(l: Lectura, before: Int,position: Int) {
        val bindingParent = ActivityLecturasBinding.inflate(activity.layoutInflater)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(context.resources.getString(R.string.lectura))
        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            lectura = input.text.toString()
            val cal = Calendar.getInstance()
            Log.e("Fecha Actual","${cal.get(Calendar.DAY_OF_MONTH)}-${cal.get(Calendar.MONTH)}")
                if(!lectura.isNullOrEmpty()){

                    l.lectura_anterior = before
                    l.lectura_actual = lectura!!.toInt()
                    l.kilovatios = lectura!!.toInt() - before
                    lecturaViewModel.update(l,l.client_id,l.id_add?:0,counter.toInt())
                    notifyItemChanged(position)

                    for (i in position+1 until lecturas.size){
                        lecturas[i].lectura_anterior = lecturas[i-1].lectura_actual
                        lecturas[i].kilovatios = lecturas[i].lectura_actual - lecturas[i-1].lectura_actual
                        lecturaViewModel.update(lecturas[i],lecturas[i].client_id,lecturas[i].id_add?:0,counter.toInt())
                        notifyItemChanged(i)
                    }

                }else
                    CustomSnackBar(activity,bindingParent.coordinator).showError(context.resources.getString(R.string.introduce_some_value))
            }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            lectura = ""
            dialog.cancel() }
        builder.show()
    }


}