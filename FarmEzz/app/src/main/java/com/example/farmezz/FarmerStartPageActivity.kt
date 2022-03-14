package com.example.farmezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FarmerStartPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_start_page)


    }
    fun addNewItem(view : View){
        // add new Intent
        val intent = Intent(this,AddNewTransactionActivity::class.java);
        startActivity(intent);
    }
}