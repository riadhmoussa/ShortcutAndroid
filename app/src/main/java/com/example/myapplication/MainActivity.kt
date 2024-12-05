package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createShortcut()
    }

    private fun createShortcut() {
        val googleUri = Uri.parse("https://www.google.com")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager

            val intent = Intent(Intent.ACTION_VIEW, googleUri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val defaultBitmap = BitmapFactory.decodeResource(resources, R.drawable.moussa)

            val icon = Icon.createWithBitmap(defaultBitmap)

            val shortcut = ShortcutInfo.Builder(this, "shortcut_google")
                .setShortLabel("Open Google")
                .setLongLabel("Go to Google")
                .setIcon(icon)
                .setIntent(intent)
                .build()

            shortcutManager.setDynamicShortcuts(listOf(shortcut))
            Toast.makeText(this, "Shortcut Created!", Toast.LENGTH_SHORT).show()
        }
    }
}
