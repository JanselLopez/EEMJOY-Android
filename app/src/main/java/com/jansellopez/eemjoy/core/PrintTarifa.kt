package com.jansellopez.eemjoy.core

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.data.model.Tarifa
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import cu.jansellopez.custom_snackbars.CustomSnackBar
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class PrintTarifa(private  val context: Context,
                  private val activity: Activity,
                  private val client: Client,
                  private val zoneName:String,
                  private val periodFull: Period,
                  private val lecturas: List<Lectura>,
                  private val tarifas: List<Tarifa>,
                  private val coordinatorLayout: CoordinatorLayout,) {
    fun print(){

        if(isStoragePermissionGranted()) {
            val logo=context.filesDir.absolutePath+"/logo.jpeg"
            try {
                val d = AppCompatResources.getDrawable(context, R.drawable.logo)
                val bitmap = (d as BitmapDrawable).bitmap
                val fileOutputStream =
                    context.openFileOutput("logo.jpeg", Context.MODE_PRIVATE)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)

                fileOutputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.e("logoPath", logo)

            val pathBase =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
            val html = File("$pathBase/Tarifa.htm")
            try {
                val myWriter = FileWriter(html)
                var htmlContent =""
                var i=0
                var sum = BigDecimal("0.0")
                var body = ""
                val cant = lecturas.filter { it.client_id == client.id }.size
                val reconnect:Double = 168.0//valor de la reconeccion
                val isReconnect = cant>=3
                if(isReconnect) sum = sum.plus(reconnect.toBigDecimal())


                lecturas.filter { it.client_id == client.id }.reversed().forEach { lectura ->
                    run breaking@{
                        tarifas.filter { it.id == lectura.tarifa_id }.forEach { tarifa ->
                            Toast.makeText(context,"Lectura anterior ${++i}: ${lectura.lectura_anterior}; tarifa:${tarifa.id}",Toast.LENGTH_LONG).show()

                            val uno = if (tarifa.cobro_real == "1") {
                                (lectura.kilovatios * tarifa.kwh) + (lectura.kilovatios * tarifa.kwh)*(tarifa.iva/100)
                            }else{
                                tarifa.range_max * tarifa.kwh + (tarifa.range_max * tarifa.kwh)*(tarifa.iva/100)
                            }
                            val dos = tarifa.fixed_charge + (tarifa.fixed_charge *(tarifa.fixed_charge_iva/100))
                            val tres = tarifa.street_lighting
                            val cuatro = if (tarifa.cobro_real == "1") {
                                lectura.kilovatios * tarifa.inde_contribution + (lectura.kilovatios * tarifa.inde_contribution*(tarifa.inde_contribution_iva/100))
                            }else{
                                tarifa.range_max * tarifa.inde_contribution + (tarifa.range_max * tarifa.inde_contribution*(tarifa.inde_contribution_iva/100))
                            }

                            var total:BigDecimal = (uno + dos + tres  - cuatro).toBigDecimal()
                            val mora:BigDecimal = if (cant >1) total.multiply((tarifa.mora/100.0).toBigDecimal()) else 0.0.toBigDecimal()
                             total += mora
                            sum = sum.plus (total)
                            Log.v("sum",sum.toString())


                            body +=
                                HtmConstants.getLecturaBody(
                                    alumbrado = ((tres * 100.0).roundToInt() / 100.0),
                                    consumo_iva = ((uno * 100.0).roundToInt() / 100.0),
                                    cargoFijo_iva = ((dos * 100.0).roundToInt() / 100.0),
                                    inde_iva = ((cuatro * 100.0).roundToInt() / 100.0),
                                    mora = (mora.setScale(2, RoundingMode.HALF_EVEN)).toDouble(),
                                    total = (total.setScale(2, RoundingMode.HALF_EVEN)).toDouble(),
                                    lecturaActual = lectura.lectura_actual,
                                    lecturaAnterior = lectura.lectura_anterior?:0,
                                    reconnect = reconnect,
                                    isReconnect = isReconnect && (lectura == lecturas.last { it.client_id == client.id })
                                )
                        }
                    }
                }
                val dateFormat = SimpleDateFormat("d MMM yyyy")
                val date = dateFormat.format(Date())
                val header= HtmConstants.getHtmHeader(
                    lector = SharedPreferenceManager.getINstance(context).token.username,
                    cliente = "${client.firstName} ${client.firstLastName} ${client.secondLastName}",
                    address = zoneName,
                    period = periodFull.title,
                    count = client.numberCount,
                    fecha = "${periodFull.paymentDate.get(Calendar.YEAR)}-${
                        (periodFull.paymentDate.get(
                            Calendar.MONTH
                        ) + 1)
                    }-${periodFull.paymentDate.get(Calendar.DAY_OF_MONTH)}",
                    sum = (sum.setScale(2, RoundingMode.HALF_EVEN)).toDouble(),
                    currentDate = date
                )
                val footer = HtmConstants.getFooter()
                htmlContent+= header+body+footer
                myWriter.write(htmlContent)
                myWriter.close()
                //
                val apkURI = FileProvider.getUriForFile(
                    context,
                    context.applicationContext
                        .packageName + ".provider", html
                )

                val install = Intent(Intent.ACTION_VIEW)
                install.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                install.setDataAndType(apkURI, "application/*")
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                try {
                    install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    context.startActivity(install)
                } catch (e: ActivityNotFoundException) {
                    // Instruct the user to install a PDF reader here, or something
                    CustomSnackBar(
                        activity,
                        coordinatorLayout
                    ).showError(context.resources.getString(R.string.instala))
                }
                //
                println("Successfully wrote to the file.")
            } catch (e: IOException) {
                println("An error occurred.")
                e.printStackTrace()
            }
        }
    }
    private fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }
}