package com.example.farmezz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import kotlin.reflect.KClass

class FarmersItemListAdapter(private val listener: FarmerItemClicked) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items :ArrayList<Transactions> = ArrayList();
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    val TAG = "FarmersItemListAdapter"
    private var database: DatabaseReference = Firebase.database.reference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmers, parent, false)
        val viewHolder = ItemViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.productDes

        holder.author.text = currentItem.farmerID
    }
    fun updateItems() {
        items.clear()
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
                                items.add(trans!!)
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
        Log.e(TAG,items.size.toString())

    }
}
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}
interface FarmerItemClicked {
    fun onItemClicked(item: Transactions)
}