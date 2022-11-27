package com.example.ugdnyakawan.Glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ugdnyakawan.databinding.ActivityMainBinding



class Glide : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //    binding.button.setOnClickListener{

            val url = "encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbcTQMBGE6JSpYyJIvk73RbhLYKHL7yQe_Yg&usqp=CAU/300"

            Glide.with(this)
                .load(url)
                .fitCenter()
                .into(binding.imageView)

        }


    }
//}