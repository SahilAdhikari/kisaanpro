package com.example.farmezz.models

data class Users(
    val uid: String = "",
    val displayName: String? = "",
//  Transactions
    var farmerItems : ArrayList<String> = ArrayList()
    )
