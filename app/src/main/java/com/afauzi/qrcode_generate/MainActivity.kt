package com.afauzi.qrcode_generate

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.afauzi.qrcode_generate.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGenerateQrCode.setOnClickListener {
            val text = binding.etData.text.toString().trim()
            val encoder = QRGEncoder(text, null, QRGContents.Type.TEXT, 800)
            encoder.colorBlack = Color.WHITE
            encoder.colorWhite = Color.BLACK
            binding.ivQrCode.setImageBitmap(encoder.bitmap)
        }
    }
}