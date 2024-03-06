package com.example.sign

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap

class MainActivity : AppCompatActivity() {
    private val imageView: ImageView by lazy {
        findViewById(R.id.imgView)
    }

    private val frameLayoutContainer: FrameLayout by lazy {
        findViewById(R.id.frameLayoutContainer)
    }

    private val btnCreate: Button by lazy {
        findViewById(R.id.btnCreate)
    }

    private val btnClear: Button by lazy {
        findViewById(R.id.btnClear)
    }

    private lateinit var signatureView: SignatureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        signatureView = SignatureView(this).apply {
            frameLayoutContainer.addView(this)
        }

        btnCreate.setOnClickListener {
            val bitmap = signatureView.drawToBitmap(Bitmap.Config.ARGB_8888)
            imageView.setImageBitmap(bitmap)
        }

        btnClear.setOnClickListener {
            signatureView.clear()
            imageView.setImageBitmap(null)
        }
    }
}