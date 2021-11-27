package br.com.dev4u

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Response

object ServicoService {

    val host = "https://dev4u.pythonanywhere.com/"

    fun getServico(context: Context): List<Servico> {
//        val servicos = mutableListOf<Servico>()
//
//        for (i in 1..5) {
//            var s = Servico()
//            s.descricao = "Servico nº $i"
//            s.vl_obra = ""
//            s.vl_total = ""
//            s.dt_inicial = "00/00/0000"
//            s.dt_final = "00/00/0000"
//            s.imagem = "https://arcondicionadorefrival.com/wp-content/uploads/2019/02/como-instalar-ar-condicionado-split-1-e1549932371685.jpg"
//            servicos.add(s)
        try {

            val to_remove = Prefs.getStringSet("to_remove") as MutableSet<String>
            for (r in to_remove){
                HttpHelper.delete("$host/servicos/${r}")
            }

            var servicos = mutableListOf<Servico>()
            val url = "$host/servicos"
            val json = HttpHelper.get(url)


            servicos = parseJson< MutableList<Servico>>(json)

            saveDB(servicos)
            return servicos
        } catch (e: Exception) {

            val servicos = DatabaseManager.getServicoDAO().findAll()
            return servicos

        }


    }
    private fun saveDB(servicos: List<Servico>){
        for (d in servicos){
            val existe = DatabaseManager.getServicoDAO().getById(d.id!!)
            if (existe == null) {
                DatabaseManager.getServicoDAO().insert(d)
            }
        }
    }

    fun removeServico(servico: Servico){
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

    fun saveServico(): Boolean{
        val url = "https://dev4u.pythonanywhere.com/servicos"
        var json = HttpHelper.get(url)
        var servicos = parseJson<MutableList<Servico>>(json)


    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }


}