package com.example.farmezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmezz.models.Transactions
import kotlinx.android.synthetic.main.activity_farmer_start_page.*

class FarmerStartPageActivity : AppCompatActivity(), FarmerItemClicked {
    private lateinit var mAdapter: FarmersItemListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_start_page)
        farmerCurrentTransactionListView.layoutManager = LinearLayoutManager(this);
        mAdapter = FarmersItemListAdapter(this);
        farmerCurrentTransactionListView.adapter = mAdapter;
        fetchData()

    }
    fun addNewItem(view : View){
        // add new Intent
        val intent = Intent(this,AddNewTransactionActivity::class.java);
        startActivity(intent);
    }
    private fun fetchData() {
        mAdapter.updateItems()
    }

    override fun onItemClicked(item: Transactions) {
        Toast.makeText(this,"The Item Clicked is : $item",Toast.LENGTH_SHORT)
    }
}