package com.example.farmezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RetailerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retailer)
    }

    fun activeOrdersOnClick(view: View) {
        val intent = Intent(this,ActiveOrdersActivity::class.java)
        startActivity(intent)
    }

}