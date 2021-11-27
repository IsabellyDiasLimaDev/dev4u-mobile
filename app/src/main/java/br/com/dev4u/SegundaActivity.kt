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
import kotlinx.android.synthetic.main.segunda_activity.*
import kotlinx.android.synthetic.main.toolbar.*


class SegundaActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    private var servicos = listOf<Servico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segunda_activity)

        setSupportActionBar(toolbar)

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

//        servicos = ServicoService.getServico(context)

        Thread {
            this.servicos = ServicoService.getServico(this)
            runOnUiThread {
                recyclerServicos?.adapter = ServicoAdapter(servicos) {onClickServico(it)}

//                enviaNotificacao(this.disciplinas.get(0))
            }
        }.start()

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
                    Toast.makeText(context, "Atualizado com sucesso.", Toast.LENGTH_LONG).show()
                }, 10000)
            }

            id == R.id.action_sair ->  {
                var intent = Intent (this, MainActivity::class.java)
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

    private fun configuraMenuLateral() {
        var toggle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.abrir,
            R.string.fechar,
        )

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_servicos -> {
                var intent = Intent (this, SegundaActivity::class.java)
                startActivity(intent)            }

            R.id.nav_orcamento -> {
                var intent = Intent (this, CadastroActivity::class.java)
                startActivity(intent)            }

            R.id.nav_sair -> {
                var intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}