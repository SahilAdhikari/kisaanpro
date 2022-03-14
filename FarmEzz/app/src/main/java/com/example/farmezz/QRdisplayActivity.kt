package com.example.farmezz

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class QRdisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrdisplay)
        Log.e("QRdisplayActivity opened","Opened");
        try {
            val intent = intent
            val bitmap = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
            val imageViewQR:ImageView=findViewById(R.id.qrCodeImageView);
            imageViewQR.setImageBitmap(bitmap)
        }
        catch ( e : Exception){
            Log.e("Error",e.message.toString())
            //e.printStackTrace();
        }

    }
}