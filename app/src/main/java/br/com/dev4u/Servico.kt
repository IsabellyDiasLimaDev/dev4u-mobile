package br.com.dev4u

import java.io.Serializable

class Servico : Serializable {
    var id: Long? = null
    var descricao = ""
    var dataInicial = ""
    var dataFinal = ""
    var imagem = ""

    override fun toString(): String {
        return "Servico(nome='$descricao')"
    }
}