package dz.esi.receiverdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CUSTOM_INTENT = "dz.esi.demobroadcast"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        btnSend.setOnClickListener {
            val i = Intent()
            i.action = CUSTOM_INTENT
            i.setClass(this, TestReceiver::class.java)
            context.sendBroadcast(i)
        }
        btnStartService.setOnClickListener { DemoService.demarrerService(context) }
    }
}
