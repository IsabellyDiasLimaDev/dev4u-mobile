package br.com.dev4u

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : AppCompatActivity() {
    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setSupportActionBar(toolbar)
        supportActionBar?.title="Novo Serviço"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btSave.setOnClickListener {

            val servico = Servico()
            servico.descricao = descricao.text.toString()
            servico.vl_obra = vl_obra.text.toString()
            servico.vl_total = vl_total.text.toString()
            servico.dt_inicial = dt_inicial.text.toString()
            servico.dt_final = dt_final.text.toString()
            servico.imagem = "https://arcondicionadorefrival.com/wp-content/uploads/2019/02/como-instalar-ar-condicionado-split-1-e1549932371685.jpg"

            taskAtualizar(servico)

            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        when {
            id == R.id.action_buscar -> Toast.makeText(
                context,
                "Botão de buscar",
                Toast.LENGTH_LONG
            ).show()
            id == R.id.action_atualizar -> Toast.makeText(
                context,
                "Botão de atualizar",
                Toast.LENGTH_LONG
            ).show()
            id == R.id.action_config -> {
                var intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }
            id == R.id.action_adicionar -> {
                var intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)
            }
            id == android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun taskAtualizar(servico: Servico): Boolean {
        Thread {
                ServicoService.saveServico(servico)
            runOnUiThread {
                finish()
            }
        }.start()
        Toast.makeText(this, "Serviço salvo com sucesso!", Toast.LENGTH_LONG).show()
        return true
    }

}