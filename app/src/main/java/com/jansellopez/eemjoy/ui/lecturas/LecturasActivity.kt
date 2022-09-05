package com.jansellopez.eemjoy.ui.lecturas

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.databinding.ActivityLecturasBinding


class LecturasActivity : AppCompatActivity() {

    private val binding:ActivityLecturasBinding by lazy { ActivityLecturasBinding.inflate(layoutInflater)}
    private var lectura:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        binding.toolbar.title = bundle!!.getString("counter")
        binding.toolbar.subtitle = bundle.getString("name")

        binding.fabAdd.setOnClickListener {
            getLectura()
            if (!lectura.isNullOrEmpty()){

            }
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getLectura(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.lectura))
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            lectura = input.text.toString()
        }
        builder.setNegativeButton(android.R.string.cancel
        ) { dialog, _ ->
            lectura = ""
            dialog.cancel() }
        builder.show()
    }
}