package com.example.farmezz.models

data class transactions(
    val transactionID: String = "",
    val farmerID: String = "",
    val logisticID: String = "",
    val retailerID: String = "",
    val productDes : String ="",

    val isRecievedByLogistic : Boolean = false,
    val isRecievedByRecieved: Boolean = false,

)
