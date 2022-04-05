package com.example.farmezz

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.farmezz.daos.TransactionDao
import com.example.farmezz.daos.UsersDao
import com.example.farmezz.models.Transactions
import com.example.farmezz.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.io.Console
import java.lang.Exception
import com.google.firebase.firestore.DocumentSnapshot

import com.google.android.gms.tasks.Task

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.firestore.DocumentReference
import java.util.*


class AddNewTransactionActivity : AppCompatActivity() {
    //Dummy variable
    var transactionID=0;
    var farmer_ID="";
    var logi_ID="";
    var retailer_ID="";

    //view
    lateinit var transidView:TextView
    lateinit var farmeridView:TextView
    lateinit var logiidView:TextView
    lateinit var retailidView:TextView
    lateinit var product_desc:EditText
    private val TAG= "AddNewTransactionActivity"
    private lateinit var  cropAddImageView :ImageView

    //dao
    //val dao : TransactionDao ;
    //Firebase
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_transaction)
        init()

    }
    fun init(){
        transactionID =   (0..10000).random()
        auth = Firebase.auth;
        farmer_ID = auth.uid!!

        logi_ID = "HxUzrxDxyQRff4lWGq1xeOOccXn2"
        retailer_ID="ADMIN_RETAILER"

        product_desc=findViewById(R.id.productDescriptionEditTextView);
        transidView= findViewById(R.id.transIDEditTextView);
        farmeridView= findViewById(R.id.farmerIDEditTextView);
        logiidView= findViewById(R.id.logisticsIDEditTextView);
        retailidView= findViewById(R.id.retailIDtextView3);

        transidView.setText(transactionID.toString())
        farmeridView.setText(farmer_ID)
        logiidView.setText(logi_ID)
        retailidView.setText(retailer_ID)

        cropAddImageView = findViewById(R.id.cropAddImageView)



    }

    fun addItemOnClick(view: View){
        Log.e("addItemOnClick opened","Opened");
        //Log.e("authid",auth.uid.toString())
        val tId = UUID.randomUUID().toString()

        val bitmap = getQrCodeBitmap(tId);
        val intent = Intent(this,QRdisplayActivity::class.java)
        intent.putExtra("BitmapImage", bitmap);
        val dao : TransactionDao = TransactionDao()
        val mTransaction = Transactions(transactionID = tId,
            farmer_ID,
            logi_ID,
            retailer_ID,
            findViewById<EditText>(R.id.productDescriptionEditTextView).text.toString(),
            false,
            false
        );
        dao.addTransaction(mTransaction,tId);
        val docRef = db.collection("users").document(auth.uid!!)
        val uDao = UsersDao();
        var userInfo : Users
        try {
            docRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documentSnapshot = task.result
                    if (documentSnapshot != null) {
                        userInfo = documentSnapshot.toObject(Users::class.java)!!
                        userInfo.farmerItems.add(mTransaction.transactionID)
                        usersCollection.document(auth.uid!!).set(userInfo)
                    }
                }
            }



        }catch (e : Exception){
            Log.e(TAG,e.toString())
        }
        startActivity(intent)
    }
    fun getQrCodeBitmap(transID: String): Bitmap {
        Log.e("getQrCodeBitmap opened","Opened");
        val size = 256 //pixels
        //val qrCodeContent = "TRANSACTION_ID:S:$transID"
        val qrCodeContent = "transactionId"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
        //Log.e("getQrCodeBitmap closed","closed");
    }

    fun getPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 1)
    }

    fun chooseImageClicked(view: View) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            getPhoto()
        }
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        try{
//            val selectedImage = data!!.data
//        }catch (e : Exception){
//            Log.e(TAG,"onActivityResult"+e.toString())
//        }
//
////        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
////            try {
////                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
////                cropAddImageView?.setImageBitmap(bitmap)
////            } catch (e: Exception) {
////                e.printStackTrace()
////            }
////
////        }
//    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto()
            }
        }
    }

}
