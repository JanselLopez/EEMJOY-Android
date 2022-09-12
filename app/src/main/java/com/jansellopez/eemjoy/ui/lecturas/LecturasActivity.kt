package com.jansellopez.eemjoy.ui.lecturas

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.databinding.ActivityLecturasBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class LecturasActivity : AppCompatActivity() {

    private val binding:ActivityLecturasBinding by lazy { ActivityLecturasBinding.inflate(layoutInflater)}
    private var lectura:String? = null
    private val lecturaViewModel:LecturaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        binding.toolbar.title = bundle!!.getString("counter")
        binding.toolbar.subtitle = bundle.getString("name")
        val clientId = bundle.getInt("clientId")
        val zone = bundle.getInt("zone")

        lecturaViewModel.onCreate(clientId)

        binding.fabAdd.setOnClickListener {
            getLectura(clientId,zone)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.rvLecturas.layoutManager = LinearLayoutManager(this)

        lecturaViewModel.lecturas.observe(this,{
            binding.rvLecturas.adapter = LecturaAdapter(it)
        })

    }

    private fun getLectura(clientId:Int,zone:Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.lectura))
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            lectura = input.text.toString()
            if(lecturaViewModel.period.value?.endDate?.timeInMillis ?:0  >System.currentTimeMillis() &&lecturaViewModel.period.value?.beginDate?.timeInMillis ?:0  <System.currentTimeMillis()){
                if(!lectura.isNullOrEmpty()){
                    val date: Date = Calendar.getInstance().time
                    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.zzz")
                    val actualDate: String = dateFormat.format(date)
                    System.currentTimeMillis()
                    lecturaViewModel.pushLectura(Lectura(
                        lecturaViewModel.lastLectura.value!!.id + 1,
                        clientId,
                        zone,
                        lecturaViewModel.period.value!!.id,
                        lecturaViewModel.lastLectura.value!!.lectura_actual,
                        resources.getString(R.string.pendiente),
                        lectura!!.toInt(),
                        lectura!!.toInt() - lecturaViewModel.lastLectura.value!!.lectura_actual,
                        0,
                        0,
                        actualDate
                    ),clientId)
                }else{
                    Toast.makeText(this,"Introduzca algun valor",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Esta fuera del periodo",Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton(android.R.string.cancel
        ) { dialog, _ ->
            lectura = ""
            dialog.cancel() }
        builder.show()
    }
}