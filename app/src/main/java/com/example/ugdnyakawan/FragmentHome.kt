package com.example.ugdnyakawan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView

import com.example.ugdnyakawan.databinding.FragmentHomeBinding

import com.example.ugdnyakawan.map.MapActivity
import kotlinx.android.synthetic.main.fragment_home.view.*


class FragmentHome : Fragment() {
    private var bindingHome: FragmentHomeBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        val view = bindingHome!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.buttonMap.setOnClickListener{
            val intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
        }
    }



}