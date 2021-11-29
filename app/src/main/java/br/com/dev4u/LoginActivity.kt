package br.com.dev4u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        var usuario = arrayListOf<Login>()
        var btLogin = findViewById(R.id.btLogin) as Button
        var etUsername = findViewById(R.id.etUsername) as EditText
        var etPassword = findViewById(R.id.etPassword) as EditText

        etUsername.setText(Prefs.getString("nome_usuario"))
        etPassword.setText(Prefs.getString("senha_usuario"))
        checkLogin.isChecked = Prefs.getBoolean("lembrar_login")

        btLogin.setOnClickListener {
            //LoginService.getLogin()
//            Thread {
//                usuario = LoginService.getLogin() as ArrayList<Login>
//                LoginService.getLogin()
//                runOnUiThread {
//                    val username = etUsername.text.toString()
//                    val password = etPassword.text.toString()
//                    val check_login = checkLogin.isChecked
//
//                    if (check_login){
//                        Prefs.setString("nome_usuario", username)
//                        Prefs.setString("senha_usuario", password)
//                    } else{
//                        Prefs.setString("nome_usuario", "")
//                        Prefs.setString("senha_usuario", "")
//                    }
//                    Prefs.setBoolean("lembrar_login", check_login)
//
//                    var intent = Intent (this, TelaInicialActivity::class.java)
//
//                    intent.putExtra("nome_usuario", username)
//                    intent.putExtra("senha_usuario", password)
//
//                    for (u in usuario) {
//                        if ( username == u.login  && password == u.senha) {
//                            showToast(getString(R.string.login_sucesso))
//                            startActivity(intent)
//                            break
//                        }
//                        else {
//                            showToast(getString(R.string.login_erro))
//                        }
//                    }
//
//                }
//
//            }.start()

            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val check_login = checkLogin.isChecked

            if (check_login){
                Prefs.setString("nome_usuario", username)
                Prefs.setString("senha_usuario", password)
            } else{
                Prefs.setString("nome_usuario", "")
                Prefs.setString("senha_usuario", "")
            }
            Prefs.setBoolean("lembrar_login", check_login)

            Thread {
                val resultadoLogin = LoginService.validarLogin(username, password)
                if (resultadoLogin.toString() == "SUCESSO"){
                    showToast(getString(R.string.login_sucesso))
                    var intent = Intent (this, TelaInicialActivity::class.java)
                    startActivity(intent)
                }
                else{
                    showToast(getString(R.string.login_erro))
                }

            }.start()
        }
    }

    fun showToast(toast: String?) {
        runOnUiThread {
            Toast.makeText(this , toast, Toast.LENGTH_SHORT).show()
        }
    }
}