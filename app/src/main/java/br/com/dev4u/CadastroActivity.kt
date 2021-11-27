package br.com.dev4u

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_servico.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : AppCompatActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btSave.setOnClickListener{
            val servico = intent.getSerializableExtra("servico") as Servico

            descricao_servico.text = servico.descricao
            valor_obra_servico.text = servico.vl_obra
            valor_total_servico.text = servico.vl_total
            dt_inicial_servico.text = servico.dt_inicial
            dt_final_servico.text = servico.dt_final
            servico.imagem = "https://arcondicionadorefrival.com/wp-content/uploads/2019/02/como-instalar-ar-condicionado-split-1-e1549932371685.jpg"

            Thread{
                saveOffline(servico)
                runOnUiThread {
                    finish()
                }
            }.start()

            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        when{
            id == R.id.action_buscar -> Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
            id == R.id.action_atualizar -> Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
            id == R.id.action_config ->{
                var intent = Intent (this, ConfigActivity::class.java)
                startActivity(intent)
            }
            id == R.id.action_adicionar -> {
                var intent = Intent (this, CadastroActivity::class.java)
                startActivity(intent)
            }
            id == android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }



    fun saveOffline(servico: Servico): Boolean{
        val servicoDao = DatabaseManager.getServicoDAO()
        servicoDao.insert(servico)

        Toast.makeText(this, "Serviço salvo com sucesso!", Toast.LENGTH_LONG).show()

        return true
    }

}