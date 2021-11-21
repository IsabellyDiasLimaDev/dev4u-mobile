package br.com.dev4u

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Response

object LoginService {
    val host = "https://dev4u.pythonanywhere.com/"
    val TAG = "WS_ClearGrant"

    fun validarLogin(usuario: String, senha: String): String {

        val url = "$host/login/${usuario}/${senha}"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        var resultadoLogin = json

        return resultadoLogin
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}