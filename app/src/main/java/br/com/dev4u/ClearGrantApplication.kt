package br.com.dev4u

import android.app.Application

class ClearGrantApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: ClearGrantApplication? = null

        fun getInstance(): ClearGrantApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar o application no Manifest")
            }
            return appInstance!!
        }
    }
}