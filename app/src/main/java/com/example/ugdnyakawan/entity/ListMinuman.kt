package com.example.ugdnyakawan.entity

import com.example.ugdnyakawan.R


class ListMinuman(var nama : String, var deskripsi : String, var gambar : Int) {

    companion object{
        @JvmField
        var listOfMinuman = arrayOf(
            ListMinuman("ES Tea", "Tea manis yang diperpadukan dengan ES yang segar", R.drawable.esteh),
            ListMinuman("ES Jeruk", "Air Jeruk manis yang diperpadukan dengan ES yang segar", R.drawable.esjeruk),
            ListMinuman("ES Jus Buah", "Berbagai macam buah yang dicampur serta diberi ES yang segar", R.drawable.esjusbuah)

        )
    }
}
