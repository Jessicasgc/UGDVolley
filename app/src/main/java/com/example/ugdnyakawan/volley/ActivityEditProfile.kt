package com.example.ugdnyakawan.volley

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.volley.api.ProfilApi
import com.example.ugdnyakawan.volley.models.Profil
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.json.JSONObject
import java.lang.reflect.Method
import java.nio.charset.StandardCharsets

class ActivityEditProfile : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var edit_username: EditText? = null
    private var edit_password: EditText? = null
    private var edit_email: EditText? = null
    private var edit_tglLahir: EditText? = null
    private var edit_notelp: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        queue = Volley.newRequestQueue(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val button: Button = findViewById(R.id.button_save)
        button_save.setOnClickListener {
            //updateProfil()
        }
    }

    private fun updateProfil(id: Long) {
        // Fungsi untuk mengubah data mahasiswa.

        val etNama= edit_username?.text.toString()
        val etPassword= edit_password?.text.toString()
        val etEmail= edit_email?.text.toString()
        val etTanggalLahir= edit_tglLahir?.text.toString()
        val etNomorTelepon= edit_notelp?.text.toString()


        val profil = Profil(
            etNama,
            etPassword,
            etEmail,
            etTanggalLahir,
            etNomorTelepon
        )

        val stringRequest: StringRequest = object :
            StringRequest(Method.PUT, ProfilApi.UPDATE_URL + id, Response.Listener { response ->
                val gson = Gson()
                var mahasiswa = gson.fromJson(response, profil::class.java)

                if(mahasiswa != null)
                    Toast.makeText(this@ActivityEditProfile, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()

//                setLoading(false)
            }, Response.ErrorListener { error ->
//                setLoading(false)
                try {
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@ActivityEditProfile,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@ActivityEditProfile, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val gson = Gson()
                val requestBody = gson.toJson(profil)
                return requestBody.toByteArray(StandardCharsets.UTF_8)
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue!!.add(stringRequest)
    }
}