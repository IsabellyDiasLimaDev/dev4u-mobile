package br.com.dev4u

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class ConfigActivity : AppCompatActivity() {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        setSupportActionBar(toolbar)
        setupNotificationSwitch()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupNotificationSwitch() {
        switch2.isChecked = Prefs.getBoolean("notificacao", true)

        switch2.setOnCheckedChangeListener { _, isChecked ->
            Prefs.setBoolean("notificacao", isChecked)
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

            id == R.id.action_sair -> {
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

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

}