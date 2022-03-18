package com.example.farmezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
    fun logisticPageOnClick(view : View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun farmerPageOnClick(view : View){
        val intent = Intent(this,FarmerStartPageActivity::class.java)
        startActivity(intent)
    }
    fun retailerPageOnClick(view: View) {
        val intent = Intent(this,RetailerActivity::class.java)
        startActivity(intent)
    }


}