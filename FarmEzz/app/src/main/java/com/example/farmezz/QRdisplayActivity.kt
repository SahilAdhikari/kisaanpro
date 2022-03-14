package com.example.farmezz

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class QRdisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrdisplay)
        val intent = intent
        val bitmap = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
        val imageViewQR:ImageView=findViewById(R.id.qrCodeImageView);
        imageViewQR.setImageBitmap(bitmap)
    }
}