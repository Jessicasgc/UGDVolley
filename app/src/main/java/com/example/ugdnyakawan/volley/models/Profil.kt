package com.example.ugdnyakawan.volley.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class Profil (@SerializedName("username") var username: String,
              @SerializedName("password") var password: String,
              @SerializedName("email") var email: String,
              @SerializedName("tgl_lahir") var tgl_Lahir: String,
              @SerializedName("username") var no_telp: String) {
    var id: Int? = null
}