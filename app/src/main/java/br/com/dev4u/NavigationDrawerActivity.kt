package br.com.dev4u

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.tela_inicial_activity.*
import kotlinx.android.synthetic.main.toolbar.*

open class NavigationDrawerActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun configuraMenuLateral() {
        var toggle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.abrir,
            R.string.fechar
        )

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_servicos -> {
                var intent = Intent (this, TelaInicialActivity::class.java)
                startActivity(intent)            }
            R.id.nav_orcamento -> {
                var intent = Intent (this, CadastroActivity::class.java)
                startActivity(intent)            }
            R.id.nav_localizacao -> {
                var intent = Intent(this, MapasActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_sair -> {
                var intent = Intent (this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}