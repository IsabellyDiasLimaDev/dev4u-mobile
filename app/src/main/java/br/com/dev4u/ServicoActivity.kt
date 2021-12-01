package br.com.dev4u

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ViewUtils.getContentView
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_servico.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.util.*
import kotlin.Exception

class ServicoActivity : AppCompatActivity()  {

    private val context: Context get() = this

    val STORAGE_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico)

        setSupportActionBar(toolbar)
        supportActionBar?.title="Detalhes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val servico = intent.getSerializableExtra("servico") as Servico
        descricao_servico.text = servico.descricao
        valor_obra_servico.text = "R$ ${servico.vl_obra}"
        valor_total_servico.text = "R$ ${servico.vl_total}"
        dt_inicial_servico.text = servico.dt_inicial
        dt_final_servico.text = servico.dt_final

        bt_remover.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir o Serviço ?")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir(servico)
                } .setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }

        bt_geraPdf.setOnClickListener {

//            val view = View(findViewById(R.id.activity_servico))

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, STORAGE_CODE)
                } else {
                    geraPdf(servico)
                }
            }
            else {
                geraPdf(servico)
            }
        }
    }

    fun taskExcluir(servico: Servico): Boolean {
        Thread {
            ServicoService.removeServico(servico)
            runOnUiThread {
                finish()
            }
        }.start()
        Toast.makeText(this, "Serviço excluído com sucesso!", Toast.LENGTH_LONG).show()
        return true
    }

    fun geraPdf(servico: Servico) {
        val documento = Document()
        val nomeArquivo = "Relatório - Serviço ${servico.id}"
        val diretorio = Environment.getExternalStorageDirectory().toString() + "/" + nomeArquivo +".pdf"

        try {

            PdfWriter.getInstance(documento, FileOutputStream(diretorio))
            documento.open()
            documento.add(Paragraph("ClearGrant - Relatório do Orçamento"))
            documento.add(Paragraph("\n\n"))
            documento.add(Paragraph("Serviço: " + servico.id.toString()))
            documento.add(Paragraph("\n"))
            documento.add(Paragraph("Descrição: "+ servico.descricao))
            documento.add(Paragraph("\n"))
            documento.add(Paragraph("Valor da Obra: R$ " + servico.vl_obra))
            documento.add(Paragraph("\n"))
            documento.add(Paragraph("Valor Total: R$ " + servico.vl_total))
            documento.add(Paragraph("\n"))
            documento.add(Paragraph("Data Inicial: " + servico.dt_inicial))
            documento.add(Paragraph("\n"))
            documento.add(Paragraph("Data Final: " + servico.dt_final))
            documento.setMargins(2F, 2F, 2F, 2F )
            documento.close()
            Toast.makeText(this, "$nomeArquivo\n foi criado com sucesso em\n$diretorio", Toast.LENGTH_SHORT).show()

        } catch (e: Exception){
            Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val servico = intent.getSerializableExtra("servico") as Servico
                    descricao_servico.text = servico.descricao
                    valor_obra_servico.text = "R$ ${servico.vl_obra}"
                    valor_total_servico.text = "R$ ${servico.vl_total}"
                    dt_inicial_servico.text = servico.dt_inicial
                    dt_final_servico.text = servico.dt_final
                    geraPdf(servico)
                } else {
                    Toast.makeText(this, "Não foi possível criar o documento: Permissão negada.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}