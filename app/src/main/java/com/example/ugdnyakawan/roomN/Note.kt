package com.example.ugdnyakawan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nama: String,
    val deskripsi: String
)