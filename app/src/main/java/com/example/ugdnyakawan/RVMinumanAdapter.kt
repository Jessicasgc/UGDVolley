package com.example.ugdnyakawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugdnyakawan.entity.ListMinuman

class RVMinumanAdapter(private val data: Array<ListMinuman>) : RecyclerView.Adapter<RVMinumanAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_minuman, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder:viewHolder, position: Int){
        val currentItem = data[position]
        holder.tvNama1.text = currentItem.nama
        holder.tvNama2.text = currentItem.nama
        holder.tvNama3.text = currentItem.nama
        holder.tvDeskripsi1.text = currentItem.deskripsi
        holder.tvDeskripsi2.text = currentItem.deskripsi
        holder.tvDeskripsi3.text = currentItem.deskripsi
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvNama1 : TextView = itemView.findViewById(R.id.imageView)
        val tvNama2 : TextView = itemView.findViewById(R.id.imageView21)
        val tvNama3 : TextView = itemView.findViewById(R.id.imageView27)

        val tvDeskripsi1 : TextView = itemView.findViewById(R.id.textView22)
        val tvDeskripsi2 : TextView = itemView.findViewById(R.id.textView2)
        val tvDeskripsi3 : TextView = itemView.findViewById(R.id.textView25)
    }
}
