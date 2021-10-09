package br.com.dev4u

import android.content.Context

object ServicoService {

    fun getServico(context: Context): List<Servico> {
        val servicos = mutableListOf<Servico>()

        for (i in 1..5) {
            var s = Servico()
            s.descricao = "Servico nº $i"
            s.dataInicial = "00/00/0000"
            s.dataFinal = "00/00/0000"
            s.imagem = "https://arcondicionadorefrival.com/wp-content/uploads/2019/02/como-instalar-ar-condicionado-split-1-e1549932371685.jpg"
            servicos.add(s)
        }

        return servicos
    }
}