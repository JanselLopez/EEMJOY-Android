package com.jansellopez.eemjoy.ui.clients.adapter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import com.jansellopez.eemjoy.R
import com.jansellopez.eemjoy.data.model.Client
import com.jansellopez.eemjoy.data.model.Lectura
import com.jansellopez.eemjoy.data.model.Period
import com.jansellopez.eemjoy.databinding.BottomsheetClientBinding
import com.jansellopez.eemjoy.databinding.CvClienteBinding
import com.jansellopez.eemjoy.ui.lecturas.LecturasActivity
import cu.jansellopez.custom_snackbars.CustomSnackBar
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.util.*
import java.util.stream.Collectors

import com.itextpdf.text.pdf.draw.VerticalPositionMark
import com.jansellopez.eemjoy.data.model.Tarifa
import com.jansellopez.eemjoy.data.userdata.SharedPreferenceManager
import android.content.ActivityNotFoundException
import android.net.Uri

import androidx.core.content.ContextCompat.startActivity
import java.io.File


class ClientAdapter(
    private val users: MutableList<Client>,
    private val zone: Int,
    private val activity: Activity,
    private val coordinatorLayout: CoordinatorLayout,
    private val lecturas: List<Lectura>,
    private val zoneName:String,
    private val periodFull: Period,
    private val tarifas: List<Tarifa>,
    private val city:Int
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

                if (str.isEmpty()) {
                    users.clear()
                    users.addAll(all)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        val collection =
                            users.stream()
                                .filter { i ->
                                    ((i.numberCount.lowercase(Locale.getDefault()).contains(
                                        str.lowercase(
                                            Locale.getDefault()
                                        )
                                    )) || ((i.firstName + i.firstLastName + i.secondLastName).lowercase(
                                        Locale.getDefault()
                                    ).contains(str.lowercase(Locale.getDefault()))))
                                }
                                .collect(Collectors.toList())
                        users.clear()
                        users.addAll(collection)
                    } else {
                        users.clear()
                        for (r in all) {
                            if (((r.numberCount.lowercase(Locale.getDefault()).contains(
                                    str.lowercase(
                                        Locale.getDefault()
                                    )
                                )) || ((r.firstName + r.firstLastName + r.secondLastName).lowercase(
                                    Locale.getDefault()
                                ).contains(str.lowercase(Locale.getDefault()))))
                            )
                                users.add(r)
                        }
                    }
                }
                notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                binding.tvContador.text = numberCount
                binding.tvName.text = "$firstName $firstLastName $secondLastName"
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
                            context.startActivity(this)
                        }
                    }
                    bindingBS.printAccount.setOnClickListener {
                        if(isStoragePermissionGranted()) {
                            val document = Document()
                            Calendar.getInstance()
                            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/Tarifa.pdf"
                            PdfWriter.getInstance(document, FileOutputStream(path))
                            document.open()
                            val d = AppCompatResources.getDrawable(context,R.drawable.header_ticker)
                            val bitmap = (d as BitmapDrawable).bitmap
                            val stream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                            val bitmapData = stream.toByteArray()
                            val header = Jpeg(bitmapData)
                            header.alignment = Image.ALIGN_CENTER
                            header.scaleToFit(PageSize.A4.width-document.leftMargin()-document.rightMargin(), PageSize.A4.height)
                            document.add(header)

                            val titleFont =
                                Font(Font.FontFamily.COURIER, 28.0f, Font.BOLD)
                            val titleParagraph = Paragraph(context.resources.getString(R.string.nombre_empresa),titleFont)
                            titleParagraph.alignment = Paragraph.ALIGN_CENTER
                            document.add(titleParagraph)
                            val normalFont =
                                Font(Font.FontFamily.COURIER, 25.0f)
                            val author = Paragraph("${context.resources.getString(R.string.lector)}: ${SharedPreferenceManager.getINstance(context).token.username}",normalFont)
                            document.add(author)

                            val dotsDivider = Paragraph(context.resources.getString(R.string.dots_divider),normalFont)
                            document.add(dotsDivider)

                            val client = Paragraph("${context.resources.getString(R.string.client)}: " +
                                    "$firstName $firstLastName $secondLastName",normalFont)
                            document.add(client)

                            val address = Paragraph("${context.resources.getString(R.string.address)}: " +
                                    zoneName,normalFont)
                            document.add(address)

                            val nit = Paragraph(context.resources.getString(R.string.nit),normalFont)
                            document.add(nit)

                            val counter = Paragraph("${context.resources.getString(R.string.no_contador)}: " +
                                    numberCount,normalFont)
                            document.add(counter)

                            document.add(dotsDivider)

                            val period = Paragraph(context.resources.getString(R.string.periodo_facturacion)+": "+ periodFull.title,normalFont)
                            document.add(period)

                            val periodSuggest = Paragraph(context.resources.getString(R.string.suggest_date)+": "
                                    + periodFull.paymentDate.get(Calendar.YEAR)+"-" + (periodFull.paymentDate.get(Calendar.MONTH)+1)+"-" +periodFull.paymentDate.get(Calendar.DAY_OF_MONTH) ,normalFont)
                            document.add(periodSuggest)

                            document.add(dotsDivider)

                            val subtitleFont =
                                Font(Font.FontFamily.COURIER, 26.0f, Font.BOLD)
                            val porPagar = Paragraph(context.resources.getString(R.string.por_pagar),subtitleFont)
                            porPagar.alignment = Paragraph.ALIGN_CENTER
                            document.add(porPagar)

                            val headText =
                                Font(Font.FontFamily.COURIER, 25.0f, Font.BOLD)

                            val glue = Chunk(VerticalPositionMark())
                            val concept = Paragraph(context.resources.getString(R.string.concept),headText)
                            concept.add(Chunk(glue))
                            concept.add(Paragraph(context.resources.getString(R.string.importe),headText))
                            document.add(concept)

                            val asteriscos = Paragraph(context.resources.getString(R.string.mult_divider),normalFont)

                            lecturas.filter { it.client_id == id }.forEach { lectura ->
                                run breaking@ {
                                    tarifas.filter { it.id == lectura.tarifa_id }.forEach {
                                        val alumbrado = Paragraph(
                                            context.resources.getString(R.string.alumbrado),
                                            normalFont
                                        )
                                        alumbrado.add(Chunk(glue))
                                        alumbrado.add(
                                            Paragraph(
                                                "Q" + it.street_lighting,
                                                normalFont
                                            )
                                        )
                                        document.add(alumbrado)
                                        val consumo = Paragraph(
                                            context.resources.getString(R.string.consumo_iva),
                                            normalFont
                                        )
                                        consumo.add(Chunk(glue))
                                        consumo.add(Paragraph("Q"+((lectura.kilovatios * it.kwh)+it.iva), normalFont))
                                        document.add(consumo)
                                        val cargoFijo = Paragraph(
                                            context.resources.getString(R.string.cargo_fijo_iva),
                                            normalFont
                                        )
                                        cargoFijo.add(Chunk(glue))
                                        cargoFijo.add(
                                            Paragraph(
                                                "Q" + (it.fixed_charge + it.fixed_charge_iva),
                                                normalFont
                                            )
                                        )
                                        document.add(cargoFijo)
                                        val inde = Paragraph(
                                            context.resources.getString(R.string.inde_iva),
                                            normalFont
                                        )
                                        inde.add(Chunk(glue))
                                        inde.add(
                                            Paragraph(
                                                "Q" + (it.inde_contribution + it.inde_contribution_iva),
                                                normalFont
                                            )
                                        )
                                        document.add(inde)
                                        val mora = Paragraph(
                                            context.resources.getString(R.string.mora),
                                            normalFont
                                        )
                                        mora.add(Chunk(glue))
                                        mora.add(Paragraph("Q" + (it.mora), normalFont))
                                        document.add(mora)
                                        val total = Paragraph(
                                            context.resources.getString(R.string.total),
                                            headText
                                        )
                                        total.add(Chunk(glue))

                                        val totalAPagar:Double = if(it.cobro_real == "1") {
                                                (((lectura.kilovatios * it.kwh)+it.iva)
                                                        + it.street_lighting + (it.fixed_charge + it.fixed_charge_iva)) - (lectura.kilovatios * it.inde_contribution)
                                            }else{
                                                (((it.range_max * it.kwh)+it.iva)
                                                        + it.street_lighting + (it.fixed_charge + it.fixed_charge_iva)) - (it.range_max* it.inde_contribution)
                                            }

                                        total.add(Paragraph("Q$totalAPagar", headText))

                                        document.add(total)
                                        return@breaking
                                    }
                                }
                                document.add(asteriscos)
                            }
                            document.close()
                            bottomSheetDialog.dismiss()
                            val file =
                                File(path)
                            val target = Intent(Intent.ACTION_VIEW)
                            target.setDataAndType(Uri.fromFile(file), "application/pdf")
                            target.flags = Intent.FLAG_ACTIVITY_NO_HISTORY

                            val intent = Intent.createChooser(target, context.resources.getString(R.string.open_pdf))
                            try {
                                context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                // Instruct the user to install a PDF reader here, or something
                                CustomSnackBar(activity,coordinatorLayout).showError(context.resources.getString(R.string.error_guardando_el_pdf))
                            }
                        }else{
                            bottomSheetDialog.dismiss()
                            CustomSnackBar(activity,coordinatorLayout).showError(context.resources.getString(R.string.otorga_permisos))
                        }
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