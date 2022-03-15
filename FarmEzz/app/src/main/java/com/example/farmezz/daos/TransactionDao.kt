package com.example.farmezz.daos

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.farmezz.models.Transactions
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class TransactionDao {

    // Write a message to the database
    private var database: DatabaseReference = Firebase.database.reference
    val postCollections = FirebaseFirestore.getInstance().collection("transactions")


    //add
    fun addTransaction(transaction : Transactions) {
        database.child("transactions").child("transactionId").setValue(transaction)
            .addOnSuccessListener {
                Log.e("addTransaction called ","added successfully");
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e("addTransaction called ","you are a failure");
            }
    }

//    fun readTransaction(pathTransaction : String) : String{
//        var mdata  = "....";
//
//        val listener = database.child(pathTransaction).get().addOnSuccessListener {
//            //Log.i("firebase read ", "Got value ${it.value}")
//            //Log.i("firebase read toString", "Got value ${it.value.toString()}")
//            mdata = it.value.toString()
//
//            //Log.i("firebase mdata", "Got value ${mdata}")
//        }.addOnFailureListener{
//            Log.e("firebase read", "Error getting data", it)
//        }
//
//
//        return mdata;
//    }
//    fun readTransaction1(pathTransaction: String):String{
//        var mdata : String = "..."
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                mdata = dataSnapshot.getValue().toString()
//                Log.e("hello",mdata)
//
//                // ...
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        database.addValueEventListener(postListener)
//
//        return mdata
//    }
//    fun getPostById(postId: String): Task<DocumentSnapshot> {
//        return postCollections.document(postId).get()
//    }






    //delete

    //retrieve

}