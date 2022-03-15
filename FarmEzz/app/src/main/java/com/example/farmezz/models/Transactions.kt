package com.example.farmezz.models

data class Transactions(
    val transactionID: String = "",
    val farmerID: String = "",
    val logisticID: String = "",
    val retailerID: String = "",
    val productDes : String ="",

    val isRecievedByLogistic : Boolean = false,
    val isRecievedByRetailer: Boolean = false,

)
