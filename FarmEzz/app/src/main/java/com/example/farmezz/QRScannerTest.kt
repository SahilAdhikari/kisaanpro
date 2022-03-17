package com.example.farmezz

import android.app.AlertDialog
import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.farmezz.daos.TransactionDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.lang.Exception



//Firebase
private val db = FirebaseFirestore.getInstance()
private val usersCollection = db.collection("users")
private lateinit var auth: FirebaseAuth
private var database: DatabaseReference = Firebase.database.reference
class QRScannerTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner_test)
        val textView: TextView = findViewById(R.id.textView)
        val qrButton: ImageButton = findViewById(R.id.qr_button)
        auth = Firebase.auth;
        qrButton.setOnClickListener({
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var result = IntentIntegrator.parseActivityResult(resultCode, data)
        val dao : TransactionDao = TransactionDao()
        //
        Log.e("firebase res",result.contents)
        database.child("transactions").child(result.contents).get().addOnSuccessListener {

            try {
                val mdata = it.value.toString()
                val logiID = it.child("logisticID").value.toString()
                Log.e("firebase read", mdata)

                if (result != null) {
                    AlertDialog.Builder(this)
                        .setMessage("Would you like to go to ${mdata}?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                            if(auth.uid==logiID ) {
                                database.child("transactions").child(result.contents)
                                    .child("recievedByLogistic").setValue(true)
                                Toast.makeText(this,"Transaction was succesful",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this,"Your are not Authenticated",Toast.LENGTH_SHORT).show()
                            }
                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .create()
                        .show()


                }
            }catch (e : Exception){
                Log.e("firebase error",e.toString())
            }



            //Log.i("firebase mdata", "Got value ${mdata}")
        }.addOnFailureListener{
            Log.e("firebase read", "Error getting data", it)
        }



        //


        //var data = task.result.toString()
        //Log.e("mydata" , data);


    }
}