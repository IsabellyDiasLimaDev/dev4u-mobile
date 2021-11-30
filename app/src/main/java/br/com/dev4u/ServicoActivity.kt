package br.com.dev4u

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_servico.*
import kotlinx.android.synthetic.main.toolbar.*

class ServicoActivity : AppCompatActivity()  {
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
}