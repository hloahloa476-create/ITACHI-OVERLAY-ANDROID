package com.itachi.overlay

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(12, 10, 20))
        }

        val title = TextView(this).apply {
            text = "ITACHI"
            textSize = 36f
            setTextColor(Color.rgb(170, 110, 255))
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "EXECUTOR • OVERLAY WORKSPACE"
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val startButton = Button(this).apply {
            text = "ACTIVATE FLOATING PANEL"
            setOnClickListener {
                if (Settings.canDrawOverlays(this@MainActivity)) {
                    startService(
                        Intent(this@MainActivity, OverlayService::class.java)
                    )
                } else {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivity(intent)
                }
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(startButton)

        setContentView(layout)
    }
}
