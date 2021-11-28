package br.com.dev4u

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Response

object ServicoService {

    val host = "https://dev4u.pythonanywhere.com"

    fun getServico(context: Context): List<Servico> {

        try {
            val to_remove = Prefs.getStringSet("to_remove") as MutableSet<String>
            for (r in to_remove) {
                HttpHelper.delete("$host/servicos/${r}")
            }

            var servicos = mutableListOf<Servico>()
            val url = "$host/servicos"
            val json = HttpHelper.get(url)

            servicos = parseJson<MutableList<Servico>>(json)

            //TODO Validar para inserir na API quando voltar a conexão com a internet

//            val servicosLocal = DatabaseManager.getServicoDAO().findAll()
//
//
//            var contador = 1
//            for (servicoBD in servicosLocal) {
//                if (servicoBD.id?.toInt()!! > servicos.lastIndex + 1) {
//                    saveServico(servicoBD)
//                }
//            }
//
//            val json2 = HttpHelper.get(url)
//
//            servicos = parseJson<MutableList<Servico>>(json2)

            saveDB(servicos)
            return servicos
        } catch (e: Exception) {
            val servicos = DatabaseManager.getServicoDAO().findAll()
            return servicos
        }
    }

    private fun saveDB(servicos: List<Servico>) {
        for (d in servicos) {
            val existe = DatabaseManager.getServicoDAO().getById(d.id!!)
            if (existe == null) {
                DatabaseManager.getServicoDAO().insert(d)
            }
        }
    }

    fun removeServico(servico: Servico) {
        try {
            HttpHelper.delete("$host/servicos/${servico.id}")
        } catch (e: Exception) {
            var to_remove = Prefs.getStringSet("to_remove") as MutableSet<String>
            to_remove.add(servico.id!!.toString())
            Prefs.setStringSet("to_remove", to_remove)
        } finally {
            DatabaseManager.getServicoDAO().delete(servico)
        }
    }

    fun saveServico(servico: Servico): br.com.dev4u.Response {
        try {
            val json = HttpHelper.post("$host/servicos", servico.toJson())
            return parseJson(json)
        } catch (t: Exception){
            saveServicoOffline(servico)
            return Response("OK", "Usuario salvo no dispositivo")
        }

    }

    fun existeServico(servico: Servico): Boolean {
        val dao = DatabaseManager.getServicoDAO()
        return dao.getByDescricao(servico.descricao) != null

    }

    fun saveServicoOffline(servico: Servico) : Boolean {
        val dao = DatabaseManager.getServicoDAO()

        if (! existeServico(servico)) {
            dao.insert(servico)
        }

        return true
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)
    }


}