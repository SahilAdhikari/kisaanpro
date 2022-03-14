package com.example.farmezz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddNewTransactionActivity : AppCompatActivity() {
    //Dummy variable
    var transactionID=0;
    var farmer_ID="";
    var logi_ID="";
    var retailer_ID="";
    var  product_desc="";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_transaction)
        init()

    }
    fun init(){
        transactionID =   (0..10000).random()
        farmer_ID = "ADMIN_FARMER"
        logi_ID = "ADMIN_LOGISTIC"
        retailer_ID="ADMIN_RETAILER"


    }
}
