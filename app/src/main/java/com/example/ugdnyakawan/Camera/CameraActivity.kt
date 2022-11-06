package com.example.ugdnyakawan.Camera
import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.Camera

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ugdnyakawan.R
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {
    private var mCamera: Camera? = null
    private var mCameraView: CameraView? = null
    private var currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        try{
            mCamera = Camera.open()
        }catch (e: Exception) {
            Log.d("Error", "Failed to get Camera" + e.message)
        }
        if(mCamera != null) {
            mCameraView = CameraView(this, mCamera!!)
            val camera_view = findViewById<View>(R.id.FlCamera) as FrameLayout
            camera_view.addView(mCameraView)
        }
        @SuppressLint("MissingInflateId", "LocalSurppress") val imageClose =
            findViewById<View>(R.id.imgClose) as ImageButton
        imageClose.setOnClickListener{ view: View? -> System.exit(0)}
        buttonChange.setOnClickListener{
            changeCamera()
        }
    }
    fun changeCamera() {
        try {
            mCamera?.stopPreview()
        } catch (e: Exception) {
            e.printStackTrace();
        }
        mCamera?.release()

        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT
        } else {
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK
        }

        try {
            mCamera = Camera.open(currentCameraId)
        } catch (e: Exception) {
            Log.d("Error", "Failed to get Camera" + e.message)
        }
        if (mCamera != null) {
            mCameraView = CameraView(this, mCamera!!)
            val camera_view = findViewById<View>(R.id.FlCamera) as FrameLayout
            camera_view.addView(mCameraView)
        }
    }



}