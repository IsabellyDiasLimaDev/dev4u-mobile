package br.com.dev4u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Entity
import kotlinx.android.synthetic.main.activity_main.*

@Entity
class MainServicoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var btSave = findViewById(R.id.btSave) as Button
        var descricao = findViewById(R.id.descricao) as EditText
        var vl_obra = findViewById(R.id.vl_obra ) as EditText
        var vl_total = findViewById(R.id.vl_total) as EditText
        var dt_inicial = findViewById(R.id.dt_inicial) as EditText
        var dt_final = findViewById(R.id.dt_final) as EditText

        descricao.setText(Prefs.getString("Descricao"))
        vl_obra.setText(Prefs.getString("Valor_mao_de_obra"))
        vl_total.setText(Prefs.getString("Valor_total"))
        dt_inicial.setText(Prefs.getString("Data_inicial"))
        dt_final.setText(Prefs.getString("Data_final"))


        btSave.setOnClickListener {
            val descricao = descricao.text.toString()
            val vl_obra = vl_obra.text.toString()
            val vl_total = vl_total.text.toString()
            val dt_inicial  = dt_inicial.text.toString()
            val dt_final  = dt_final.text.toString()


        }
    }

    fun showToast(toast: String?) {
        runOnUiThread {
            Toast.makeText(this , toast, Toast.LENGTH_SHORT).show()
        }
    }
}

