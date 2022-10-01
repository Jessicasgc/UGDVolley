package com.example.ugdnyakawan.entity

import com.example.ugdnyakawan.R

class ListMakanan (var nama : String, var deskripsi : String, var gambar : Int) {

    companion object{
        @JvmField
        var listOfMakanan = arrayOf(
            ListMakanan("Nasi Goreng", "Nasi goreng dengan isian Telur, Sosis, Timun, Kerupuk", R.drawable.nasigoreng),
            ListMakanan("Ramen", "Mie ala jepang dengan isian telur, daging babi, daun bawang cincang, wijen, kuah kaldu ala jepang", R.drawable.ramen),
            ListMakanan("Steak", "Daging Wagyu, wortel, buncis, jagung, kentang goreng dengan saus jamur", R.drawable.steak)


            )
    }
}