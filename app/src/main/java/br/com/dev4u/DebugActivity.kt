package br.com.dev4u

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class DebugActivity : AppCompatActivity() {

    protected val className: String
        get() {
            // br.com.lucasrds.lmsapp.DebugActivity
            val s = javaClass.name
            // .DebugActivity
            return s.substring(s.lastIndexOf("."))
        }
    private val TAG = "LMSApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "${className}.onCreate() chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "${className}.onStart() chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "${className}.onRestart() chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "${className}.onResume() chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "${className}.onPause() chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "${className}..onStop() chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "${className}..onDestroy() chamado")
    }
}