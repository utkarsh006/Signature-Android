package com.example.sign

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.example.sign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var signatureView: SignatureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        signatureView = SignatureView(this).apply {
            binding.frameLayoutContainer.addView(this)
        }

        binding.btnCreate.setOnClickListener {
            val bitmap = signatureView.drawToBitmap(Bitmap.Config.ARGB_8888)
            binding.imgView.setImageBitmap(bitmap)
        }

        binding.btnClear.setOnClickListener {
            signatureView.clear()
            binding.imgView.setImageBitmap(null)
        }
    }
}
