package com.afauzi.qrcode_generate

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat
import com.afauzi.qrcode_generate.databinding.ActivityMainBinding
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.style.Color
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.createQrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGenerateQrCode.setOnClickListener {

            try {
                val text = binding.etData.text.toString().trim()
                val data = QrData.Url(text)
                val option = createQrVectorOptions {
                    padding = .125F
                    logo {
                        drawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_background)
                        size = .25f
                        padding = QrVectorLogoPadding.Natural(.2f)
                        shape = QrVectorLogoShape.Circle
                    }
                    colors {
                        dark = QrVectorColor
                            .Solid(Color(0xff345288))
                        ball = QrVectorColor.Solid(
                            ContextCompat.getColor(this@MainActivity, R.color.teal_700)
                        )
                    }
                    shapes {
                        darkPixel = QrVectorPixelShape
                            .RoundCorners(.5f)
                        ball = QrVectorBallShape
                            .RoundCorners(.25f)
                        frame = QrVectorFrameShape
                            .RoundCorners(.25f)
                    }
                }
                val drawable = QrCodeDrawable(data, option)
                binding.ivQrCode.setImageDrawable(drawable)
            }catch (e: WriterException) {
                binding.tvHelperVisibility.text = "Error generate qrcode"
            }
        }
    }
}