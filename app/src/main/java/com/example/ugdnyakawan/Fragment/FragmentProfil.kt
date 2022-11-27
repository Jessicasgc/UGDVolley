package com.example.ugdnyakawan.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugdnyakawan.Camera.CameraActivity
import com.example.ugdnyakawan.databinding.FragmentProfilBinding
import kotlinx.android.synthetic.main.activity_edit_profile.view.*


    class FragmentProfil : Fragment() {
        private var bindingProfil: FragmentProfilBinding?=null
        private val binding get() = bindingProfil!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            bindingProfil = FragmentProfilBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
//            binding.button.setOnClickListener{
//                val intent = Intent(this.activity, CameraActivity::class.java)
//                startActivity(intent)
//                }
            view.button.setOnClickListener {
                val intent = Intent(this.activity, CameraActivity::class.java)
                startActivity(intent)
            }
    }
}