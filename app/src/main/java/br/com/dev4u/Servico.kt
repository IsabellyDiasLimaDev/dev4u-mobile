package br.com.dev4u

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "servico")
class Servico : Serializable {

    @PrimaryKey
    var id: Long? = null
    var descricao = ""
    var vl_obra = ""
    var vl_total = ""
    var dt_inicial = ""
    var dt_final = ""
    var imagem = ""

    override fun toString(): String {
        return "Servico(nome='$descricao')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}