package com.example.ugdnyakawan.volley.models

import com.google.gson.annotations.SerializedName

class Pemesanan (@SerializedName("nama_menu") var nama_menu: String,
                 @SerializedName("jumlah") var jumlah: Integer,
                 @SerializedName("harga") var harga: Float) {
    var idpesanan: Long? = null
}