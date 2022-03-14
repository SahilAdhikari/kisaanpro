package com.example.farmezz

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import org.w3c.dom.Text

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

        product_desc=findViewById(R.id.productDescriptionEditTextView);
        transidView= findViewById(R.id.transIDEditTextView);
        farmeridView= findViewById(R.id.farmerIDEditTextView);
        logiidView= findViewById(R.id.logisticsIDEditTextView);
        retailidView= findViewById(R.id.retailIDtextView3);

        transidView.setText(transactionID.toString())
        farmeridView.setText(farmer_ID)
        logiidView.setText(logi_ID)
        retailidView.setText(retailer_ID)

    }

    fun addItemOnClick(view: View){
        val bitmap = getQrCodeBitmap(transactionID.toString());
        val intent = Intent(this,QRdisplayActivity::class.java)
        intent.putExtra("BitmapImage", bitmap);
        startActivity(intent)
    }
    fun getQrCodeBitmap(transID: String): Bitmap {
        val size = 512 //pixels
        val qrCodeContent = "TRANSACTION_ID:S:$transID"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

}
