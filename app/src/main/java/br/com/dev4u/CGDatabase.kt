package br.com.dev4u

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Servico::class, Login::class), version = 2)
abstract class CGDatabase : RoomDatabase() {
    abstract fun servicoDAO(): ServicoDAO
    abstract fun loginDAO(): LoginDAO
}