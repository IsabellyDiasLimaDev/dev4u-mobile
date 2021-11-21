package br.com.dev4u

import android.content.SharedPreferences

object Prefs {

    private fun prefs(): SharedPreferences {
        val context = ClearGrantApplication.getInstance().applicationContext
        return context.getSharedPreferences("LMS", 0)
    }

    fun setBoolean(flag: String, valor: Boolean) {
        prefs().edit().putBoolean(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean {
        return prefs().getBoolean(flag, false)
    }

    fun setString(flag: String, valor: String) {
        prefs().edit().putString(flag, valor).apply()
    }

    fun getString(flag: String): String {
        return prefs().getString(flag, "")!!
    }

    fun setStringSet(flag: String, valor: Set<String>) {
        prefs().edit().putStringSet(flag, valor).apply()
    }

    fun getStringSet(flag: String): Set<String> {
        return prefs().getStringSet(flag, mutableSetOf<String>())!!
    }
}