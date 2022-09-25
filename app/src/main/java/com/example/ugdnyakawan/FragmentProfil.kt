package com.example.ugdnyakawan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugdnyakawan.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "username"
private const val ARG_PARAM2 = "password"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProfil : Fragment() {
    // TODO: Rename and change types of parameters
    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_PARAM1)
            password = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
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