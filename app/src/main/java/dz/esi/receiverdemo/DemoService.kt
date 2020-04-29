package dz.esi.receiverdemo

import android.R
import android.app.*
import android.content.Intent
import android.content.Context

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class DemoService : IntentService("DemoService") {



    override fun onHandleIntent(intent: Intent?) {
        try {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationID = 1001
            val pIntent = PendingIntent.getService(this, System.currentTimeMillis().toInt(), intent!!, 0)

            val builder = builder(notificationManager, pIntent)
            var noti = builder!!.build()

            notificationManager.notify(notificationID, noti)
            Thread.sleep(6000)
            builder.setProgress(100, 33, false)
            noti = builder.build()
            notificationManager.notify(notificationID, noti)
            Thread.sleep(6000)
            builder.setProgress(100, 66, false)
            noti = builder.build()
            notificationManager.notify(notificationID, noti)
            Thread.sleep(6000)
            builder.setProgress(100, 100, false).setContentText("Terminé !")
            noti = builder.build()
            notificationManager.notify(notificationID, noti)

        } catch (ex: Exception) {

        }

    }

    private fun builder(nm: NotificationManager, pi: PendingIntent): Notification.Builder? {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                    "ch00", "ch00", NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(mChannel)
            val builder = Notification.Builder(this,"ch00")
                    .setContentTitle("Service")
                    .setContentText("Notification à partir d'un service").setSmallIcon(R.drawable.btn_star)
                    .setContentIntent(pi).setAutoCancel(true)
                    .setOngoing(true)
                    .setProgress(100, 0, false)
                    .setAutoCancel(true)
            return builder


        }else{

            val builder = Notification.Builder(this)
                    .setContentTitle("Service")
                    .setContentText("Notification à partir d'un service").setSmallIcon(R.drawable.btn_star)
                    .setContentIntent(pi).setAutoCancel(true)
                    .setOngoing(true)
                    .setProgress(100, 0, false)
                    .setAutoCancel(true)
            return builder

        }

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        // TODO: Handle action Foo
        throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        // TODO: Handle action Baz
        throw UnsupportedOperationException("Not yet implemented")
    }

    companion object {
        private val ACTION_SERVICE = "dz.esi.demonotifsser"

        fun demarrerService(context: Context) {
            val intent = Intent(context, DemoService::class.java)
            intent.action = ACTION_SERVICE
            context.startService(intent)
        }
    }
}
