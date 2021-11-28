package br.com.dev4u

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "login")
class Login : Serializable {

    @PrimaryKey
    var login = ""
    var senha = ""

    override fun toString(): String {
        return "Login(login='$login')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}