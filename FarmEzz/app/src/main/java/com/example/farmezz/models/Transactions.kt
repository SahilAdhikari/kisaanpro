package com.example.farmezz.models

import java.util.*

data class Transactions(
    val transactionID: String = "",
    val farmerID: String = "",
    val logisticID: String = "",
    val retailerID: String = "",
    val productDes : String ="",
    val isRecievedByLogistic : Boolean = false,
    val isRecievedByRetailer: Boolean = false,

)