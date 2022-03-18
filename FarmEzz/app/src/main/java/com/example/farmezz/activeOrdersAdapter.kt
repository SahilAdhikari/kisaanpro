package com.example.farmezz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmezz.models.Transactions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActiveOrdersAdapter(private val orderListener: OrderClicked):RecyclerView.Adapter<ActiveOrdersViewHolder>() {
    private val orders:ArrayList<Transactions> = ArrayList();
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    private var database: DatabaseReference = Firebase.database.reference
    val TAG = "FarmersItemListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrdersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_activeorders,parent,false)
        val viewHolder = ActiveOrdersViewHolder(view)
        view.setOnClickListener{
            orderListener.onOrderClicked(orders[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ActiveOrdersViewHolder, position: Int) {
        val currentActiveOrder = orders[position]
        holder.activeOrdersView.text =currentActiveOrder.productDes
    }

    override fun getItemCount(): Int {
//        try {
//            return orders.size
//        }
//        catch (e:Exception){
//            Log.e(TAG,e.toString())
//        }
        return orders.size
    }

    fun updateItems() {
        orders.clear()
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        val docRef = db.collection("users").document(auth.uid!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    val mTransactions = document.data?.get("farmerItems") as ArrayList<String>
                    for(transactions in mTransactions){
                        database.child("transactions").child(transactions).get().addOnSuccessListener {
                            try{
                                Log.d(TAG,transactions)
                                val trans : Transactions? = it.getValue<Transactions>()
                                Log.e(TAG,trans.toString())
                                orders.add(trans!!)
                                notifyDataSetChanged()

                            }catch (e : Exception){
                                Log.e(TAG,e.toString())
                            }
                        }.addOnFailureListener{
                            Log.e(TAG, "Error getting data", it)
                        }
                    }


                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        Log.e(TAG,orders.size.toString())

    }
}

class ActiveOrdersViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val activeOrdersView: TextView = itemView.findViewById(R.id.itemsActiveOrders)
}

interface OrderClicked{
    fun onOrderClicked(order: Transactions)
}