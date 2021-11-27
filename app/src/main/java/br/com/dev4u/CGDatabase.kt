package br.com.dev4u

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Servico::class), version = 1)
abstract class CGDatabase: RoomDatabase(){
    abstract fun servicoDAO() : ServicoDAO
}