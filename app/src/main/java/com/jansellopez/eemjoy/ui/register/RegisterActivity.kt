package com.jansellopez.eemjoy.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding:ActivityRegisterBinding by lazy{ ActivityRegisterBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.etDateBorn.setOnClickListener {
            val datePicker = DatePickerFragment { day, month, year ->
                onDateSelected(
                    day,
                    month,
                    year
                )
            }
            datePicker.show(supportFragmentManager,"bornPicker")

        }
    }

    private fun onDateSelected(day:Int, month:Int, year:Int){
        binding.etDateBorn.setText("$day/$month/$year")
    }
}
