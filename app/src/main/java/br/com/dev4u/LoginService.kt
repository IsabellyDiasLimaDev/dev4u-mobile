package br.com.dev4u

import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Response

object LoginService {
    val host = "https://dev4u.pythonanywhere.com/"

//    fun getLogin(): List<Login> {
//        var usuario = mutableListOf<Login>()
//        val url = "$host/usuario"
//        val json = HttpHelper.get(url)
//        usuario = parseJson<MutableList<Login>>(json)
//
//        for (u in usuario) {
//            saveUsuarioOffline(u)
//        }
//
//        return usuario
//    }

    fun validarLogin(usuario: String, senha: String): String {

        val url = "$host/login/${usuario}/${senha}"
        val json = HttpHelper.get(url)

        var resultadoLogin = json

        return resultadoLogin

//        var resultadoLogin = ""
//        try {
//            val url = "$host/login/${usuario}/${senha}"
//            val json = HttpHelper.get(url)
//            resultadoLogin = json
//        } catch (e: Exception) {
//            if (usuario == "aluno" && senha == "impacta"){
//                resultadoLogin = "SUCESSO"
//            }
//            else{
//                resultadoLogin = "ERRO"
//            }
//        } finally {
//        }
//        return resultadoLogin
    }

//    fun saveUsuarioOffline(usuario: Login) : Boolean {
//        val dao = DatabaseManager.getLoginDAO()
//
//        dao.insert(usuario)
//
//        return true
//    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}