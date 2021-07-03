package com.example.pushalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        createNotificationChannel()

        val type = remoteMessage.data["type"]
            ?.let { NotificationType.valueOf(it)}
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        type ?: return


        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, message))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(type: NotificationType, title: String?, message: String?): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        }

        val pendingIntent = PendingIntent.getActivity(this, type.id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        // FLAG_UPDATE_CURRENT를 줌으로써 알림이 여러개더라도 각각에 맞는 펜딩 인텐트가 수행되도록 함

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 오레오 버전을 위한 우선순위
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // 클릭시 자동으로 알림 사라지게

        when(type){
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText("😀 😃 😄 😁 😆 😅 😂 🤣 🥲 +" +
                            "😊 😇 🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 😝 😜 🤪 🤨 🧐 🤓 😎 " +
                            "🤩 🥳 😏 😒 😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 😭 😤 😠 😡 🤬 "+
                            "🤯 😳 🥵 🥶 😱 😨 😰 😥 😓 🤗 🤔 🤭 🤫 🤥 😶 😐 😑 😬 🙄 😯 😦 😧 😮 "+
                            "😲 🥱 😴 🤤 😪 😵 🤐 🥴 🤢 🤮 🤧 😷 🤒 🤕 🤑 🤠 😈 👿 👹 👺 🤡 💩 👻 💀 ☠"+
                            "👽 👾 🤖 🎃 😺 😸 😹 😻 😼 😽 🙀 😿 😾"
                    )
                )
            }
            NotificationType.CUSTOM -> {
                notificationBuilder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(RemoteViews(packageName, R.layout.view_custon_notification)
                        .apply {
                            setTextViewText(R.id.title, title)
                            setTextViewText(R.id.message, message)
                        }
                    )
            }
        }

        return notificationBuilder.build()
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}