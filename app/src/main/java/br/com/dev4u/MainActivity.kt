package br.com.dev4u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btLogin = findViewById(R.id.btLogin) as Button
        var etUsername = findViewById(R.id.etUsername) as EditText
        var etPassword = findViewById(R.id.etPassword) as EditText

        btLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "aluno" && password == "impacta"){
                Toast.makeText(this, "usuario e senha corretos", Toast.LENGTH_LONG).show()
                var itent = Intent (this, SegundaActivity::class.java)
                startActivity(itent)
            }
            else{
                Toast.makeText(this, "usuario e senha incorretos", Toast.LENGTH_LONG).show()
            }
        }
    }
}