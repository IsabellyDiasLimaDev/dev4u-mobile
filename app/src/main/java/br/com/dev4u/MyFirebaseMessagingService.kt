package br.com.dev4u

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    // recebe o novo token criado
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("dev4ufirebase", "Novo token: $token")
        // Guarda o token em Shared Preferences
        Prefs.setString("FB_TOKEN", token!!)
    }

    // recebe a notificação de push
    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d("dev4ufirebase", "onMessageReceived")
        // verifica se a mensagem recebida do firebase é uma notificação
        val temNotificacaoHabilitada = Prefs.getBoolean("notificacao", true)
        if (mensagemRemota?.notification != null && temNotificacaoHabilitada) {
            val titulo = mensagemRemota?.notification?.title
            var corpo = mensagemRemota?.notification?.body
            Log.d("dev4ufirebase", "Titulo da mensagem: $titulo")
            Log.d("dev4ufirebase", "Corpo da mensagem: $corpo")

            if (mensagemRemota?.data.isNotEmpty()) {
                val servicoId = mensagemRemota.data.get("servicoId")
                corpo += " $servicoId"
            }

            val intent = Intent(this, ServicoActivity::class.java)
            NotificationUtil.create(1, intent, titulo!!, corpo!!)
        }
    }
}