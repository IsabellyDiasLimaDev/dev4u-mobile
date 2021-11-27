package br.com.dev4u

import androidx.room.Room

object DatabaseManager {
    private var dbInstance: CGDatabase

    init {
        val context =  ClearGrantApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            CGDatabase::class.java,
            "CG.sqlite"
        ).build()
    }

    fun getServicoDAO(): ServicoDAO{
        return dbInstance.servicoDAO()
    }
}