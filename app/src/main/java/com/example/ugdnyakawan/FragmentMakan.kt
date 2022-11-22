package com.example.ugdnyakawan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.RV.RVMakananAdapter
import com.example.ugdnyakawan.entity.ListMakanan


class FragmentMakan : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_makan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVMakananAdapter = RVMakananAdapter(ListMakanan.listOfMakanan)

        val rvMakanan : RecyclerView = view.findViewById(R.id.rv_makanan)

        rvMakanan.layoutManager = layoutManager

        rvMakanan.setHasFixedSize(true)

        rvMakanan.adapter = adapter

    }

}