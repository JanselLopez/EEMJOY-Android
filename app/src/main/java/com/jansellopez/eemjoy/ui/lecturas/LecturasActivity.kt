package com.jansellopez.eemjoy.ui.lecturas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.databinding.ActivityLecturasBinding
import com.jansellopez.eemjoy.ui.clients.ClientsActivity
import com.jansellopez.eemjoy.ui.clients.ClientsViewModel
import cu.jansellopez.custom_snackbars.CustomSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import android.view.MotionEvent
import android.view.View
import androidx.core.view.isVisible


@AndroidEntryPoint
class LecturasActivity : AppCompatActivity() {

    private val binding:ActivityLecturasBinding by lazy { ActivityLecturasBinding.inflate(layoutInflater)}
    private var lectura:String? = null
    private val lecturaViewModel:LecturaViewModel by viewModels()
    private var zone:Int? = null
    private var city:Int? = null
    private var zoneName:String? = null
    private var dX = 0f
    var dY: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        binding.toolbar.title = bundle!!.getString("counter")
        binding.toolbar.subtitle = bundle.getString("name")
        val clientId = bundle.getInt("clientId")
        zone = bundle.getInt("zone")
        city = bundle!!.getInt("city")
        zoneName = bundle.getString("zone_name")

        lecturaViewModel.onCreate(clientId)

        binding.fabAdd.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> view.animate()
                    .x(event.rawX + dX)
                    .y(event.rawY + dY)
                    .setDuration(0)
                    .start()
                else ->{
                    getLectura(clientId, zone!!)
                    false
                }
            }
            true
        }

        binding.toolbar.setNavigationOnClickListener {
            loadBack()
        }

        binding.rvLecturas.layoutManager = LinearLayoutManager(this)

        lecturaViewModel.lecturas.observe(this,{
            binding.rvLecturas.adapter = LecturaAdapter(it,lecturaViewModel,this,
                (bundle!!.getString("counter")?:0) as String
            )
        })

        lecturaViewModel.loading.observe(this,{
            binding.progressCircular.isVisible = it
            /*binding.btnUpload.isVisible = !it*/
        })

        /*binding.btnUpload.setOnClickListener {
            lecturaViewModel.pushAll(this)
        }*/

    }

    override fun onBackPressed() {
        loadBack()
    }

    private fun loadBack(){
        Intent(this,ClientsActivity::class.java).apply {
            putExtra("city",city)
            putExtra("zone",zone)
            putExtra("zone_name",zoneName)
            startActivity(this)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getLectura(clientId:Int, zone:Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.lectura))
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            lectura = input.text.toString()
            val cal = Calendar.getInstance()
            Log.e("Fecha Actual","${cal.get(Calendar.DAY_OF_MONTH)}-${cal.get(Calendar.MONTH)}")
            if(lecturaViewModel.period.value?.endDate?.timeInMillis ?:0  >System.currentTimeMillis() &&lecturaViewModel.period.value?.beginDate?.timeInMillis ?:0  <System.currentTimeMillis()){
                if(!lectura.isNullOrEmpty()){

                    val date: Date = Calendar.getInstance().time
                    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.zzz")
                    val actualDate: String = dateFormat.format(date)
                    System.currentTimeMillis()

                    lecturaViewModel.pushLectura(
                        Lectura(
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
                            actualDate,
                            0,
                            0
                        ), this,
                        intent.extras!!.getString("counter")!!.toInt(),
                        clientId
                    ,this,
                        binding.coordinator)
                }else
                    CustomSnackBar(this,binding.coordinator).showError(resources.getString(R.string.introduce_some_value))
            }else
                CustomSnackBar(this,binding.coordinator).showError(resources.getString(R.string.outside))
        }
        builder.setNegativeButton(android.R.string.cancel
        ) { dialog, _ ->
            lectura = ""
            dialog.cancel() }
        builder.show()
    }
}