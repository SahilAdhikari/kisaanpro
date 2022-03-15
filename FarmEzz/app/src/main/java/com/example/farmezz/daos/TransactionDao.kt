package com.example.farmezz.daos

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TransactionDao {

    // Write a message to the database
    private var database: DatabaseReference = Firebase.database.reference


    //add
    fun addTransaction(text: String) {
        database.child("transactionID").setValue(text)
            .addOnSuccessListener {
                Log.e("addTransaction called ","added successfully");
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e("addTransaction called ","you are a failure");
            }
    }



    //delete

    //retrieve

}