package br.com.dev4u;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
interface LoginDAO {

    @Query("SELECT * FROM login")
    fun findAll(): List<Login>

    @Insert
    fun insert(login: Login)

}
