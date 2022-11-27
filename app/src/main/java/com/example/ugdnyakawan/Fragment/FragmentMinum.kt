package com.example.ugdnyakawan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.RV.RVMinumanAdapter
import com.example.ugdnyakawan.entity.ListMinuman


class FragmentMinum : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_minum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVMinumanAdapter = RVMinumanAdapter(ListMinuman.listOfMinuman)

        val rvMahasiswa : RecyclerView = view.findViewById(R.id.rv_minuman)

        rvMahasiswa.layoutManager = layoutManager

        rvMahasiswa.setHasFixedSize(true)
        rvMahasiswa.adapter = adapter
    }

}