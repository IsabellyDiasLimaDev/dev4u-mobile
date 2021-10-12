package br.com.dev4u

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.segunda_activity.*
import kotlinx.android.synthetic.main.toolbar.*


class SegundaActivity: AppCompatActivity() {

    private val context: Context get() = this

    private var servicos = listOf<Servico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segunda_activity)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerServicos?.layoutManager = LinearLayoutManager(this)
        recyclerServicos?.itemAnimator = DefaultItemAnimator()
        recyclerServicos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()

        taskServicos()
    }

    fun taskServicos() {
        servicos = ServicoService.getServico(context)

        recyclerServicos?.adapter = ServicoAdapter(servicos) {onClickServico(it)}
    }

    fun onClickServico(servico: Servico) {
        Toast.makeText(context, "Data Inicial ${servico.dataInicial} - Data Final ${servico.dataFinal}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, SegundaActivity::class.java)
        intent.putExtra("servico", servico)
        //startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
                override fun onQueryTextSubmit(query: String): Boolean {
                    Toast.makeText(context, "${query}", Toast.LENGTH_LONG).show()
                    return false
                }
            })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        when{
            id == R.id.action_buscar -> Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
            id == R.id.action_atualizar -> {
                Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()

                progressSegundaActivity.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    progressSegundaActivity.visibility = View.GONE
                    Toast.makeText(context, "Atualizado com sucesso.", Toast.LENGTH_LONG).show()
                }, 10000)
            }
            id == R.id.action_config -> Toast.makeText(context, "Botão de configurações", Toast.LENGTH_LONG).show()
            id == R.id.action_adicionar -> {
                var intent = Intent (this, CadastroActivity::class.java)
                startActivity(intent)
            }
            id == android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

}