package com.example.ugdnyakawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugdnyakawan.entity.ListMinuman

class RVMinumanAdapter(private val data: Array<ListMinuman>) : RecyclerView.Adapter<RVMinumanAdapter.viewHolder>(){

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_minuman, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNama.text = currentItem.nama
        holder.tvDeskripsi.text = currentItem.deskripsi
        holder.image.setImageResource(currentItem.gambar)
    }

    override fun getItemCount(): Int{
        return data.size
    }

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvNama : TextView = itemView.findViewById(R.id.tvJudul)
        val image : ImageView = itemView.findViewById((R.id.image))
        val tvDeskripsi : TextView = itemView.findViewById(R.id.tvDesk)

    }
}