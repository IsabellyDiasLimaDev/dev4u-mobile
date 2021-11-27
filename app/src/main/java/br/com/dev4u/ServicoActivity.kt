package br.com.dev4u

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_servico.*

class ServicoActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico)

        val servico = intent.getSerializableExtra("servico") as Servico

        descricao_servico.text = servico.descricao
        valor_obra_servico.text = servico.vl_obra
        valor_total_servico.text = servico.vl_total
        dt_inicial_servico.text = servico.dt_inicial
        dt_final_servico.text = servico.dt_final

        bt_remover.setOnClickListener {
            Thread {
                ServicoService.removeServico(servico)

                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }
}