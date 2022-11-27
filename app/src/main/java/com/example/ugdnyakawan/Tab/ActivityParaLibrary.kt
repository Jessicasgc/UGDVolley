package com.example.ugdnyakawan.Tab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.RegisLogin.MainActivity
import com.example.ugdnyakawan.databinding.ActivityParaLibraryBinding
import com.example.ugdnyakawan.databinding.ActivityQractivityBinding

class ActivityParaLibrary : AppCompatActivity() {
    private var binding : ActivityParaLibraryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParaLibraryBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        binding!!.qr.setOnClickListener{
            val intent = Intent(this, ActivityQractivityBinding::class.java)
            startActivity(intent)
        }

        binding!!.chart.setOnClickListener{
            val intent = Intent(this, Chart::class.java)
            startActivity(intent)
        }
    }

}