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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.tela_inicial_activity.*
import kotlinx.android.synthetic.main.toolbar.*


class TelaInicialActivity: NavigationDrawerActivity() {

    private val context: Context get() = this
    private var servicos = listOf<Servico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.title="Início"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        recyclerServicos?.layoutManager = LinearLayoutManager(this)
        recyclerServicos?.itemAnimator = DefaultItemAnimator()
        recyclerServicos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskServicos()
    }

    fun taskServicos() {
        Thread {
            this.servicos = ServicoService.getServico(this)
            runOnUiThread {
                recyclerServicos?.adapter = ServicoAdapter(servicos) {onClickServico(it)}
                enviaNotificacao(this.servicos.get(0))
            }
        }.start()
    }

    fun enviaNotificacao(servico: Servico){
        val intent = Intent(this, ServicoActivity:: class.java)
        intent.putExtra("servico", servico)
        NotificationUtil.create(1, intent, "Clear Grant", "O serviço ${servico.id} está pendente e terminará em: ${servico.dt_final}")
    }

    fun onClickServico(servico: Servico) {
        Toast.makeText(context, "Clicou no Serviço ${servico.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ServicoActivity::class.java)
        intent.putExtra("servico", servico)
        startActivity(intent)
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
                    taskServicos()
                    Toast.makeText(context, "Atualizado com sucesso.", Toast.LENGTH_LONG).show()
                }, 10000)
            }

            id == R.id.action_sair ->  {
                var intent = Intent (this, LoginActivity::class.java)
                startActivity(intent)
            }

            id == R.id.action_config ->  {
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
}