package com.example.ugdnyakawan.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugdnyakawan.Camera.CameraActivity
import com.example.ugdnyakawan.databinding.FragmentProfilBinding
import com.example.ugdnyakawan.map.MapActivity
import kotlinx.android.synthetic.main.activity_edit_profile.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "username"
private const val ARG_PARAM2 = "password"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfil.newInstance] factory method to
 * create an instance of this fragment.
 */ // TODO: Rename and change types of parameters


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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentProfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(username: String, password: String) =
            FragmentProfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, username)
                    putString(ARG_PARAM2, password)
                }
            }
    }
}