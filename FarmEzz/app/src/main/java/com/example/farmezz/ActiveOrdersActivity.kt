package com.example.farmezz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceControl
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmezz.models.Transactions
import kotlinx.android.synthetic.main.activity_active_orders.*
//val TAG = "ActiveOrdersAdapter"

class ActiveOrdersActivity : AppCompatActivity(), OrderClicked {
    private lateinit var adapter: ActiveOrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_orders)
        activeOrdersView.layoutManager = LinearLayoutManager(this)
        adapter = ActiveOrdersAdapter(this)
        activeOrdersView.adapter = adapter
//        try {
//            activeOrdersView.adapter = adapter
//        }
//        catch (e:Exception){
//            Log.e(TAG,e.toString())
//        }
        fetchData()

    }
    private fun fetchData(){
//        val list = ArrayList<String>()
//        for(i in 0 until 100){
//            list.add("Item $i")
//        }
//        return list
        adapter.updateItems()
    }

    override fun onOrderClicked(order: Transactions) {
        Toast.makeText(this,"Order is clicked $order", Toast.LENGTH_SHORT)
        
    }
}