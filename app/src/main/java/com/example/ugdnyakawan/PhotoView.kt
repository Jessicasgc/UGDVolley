package com.example.ugdnyakawan

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView

class PhotoView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photoview)

        val photoView = findViewById<PhotoView>(R.id.photo_view)
        photoView.setImageResource(R.drawable.nasigoreng)
    }
}