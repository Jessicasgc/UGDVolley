package com.example.ugdnyakawan.Tab

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ugdnyakawan.*
import com.example.ugdnyakawan.Fragment.*
import com.example.ugdnyakawan.databinding.ActivityHome1Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.system.exitProcess

class HomeActivity1 : AppCompatActivity() {

    lateinit var binding: ActivityHome1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val fh = FragmentHome()
        val fp = FragmentProfil()
        val fma = FragmentMakan()
        val fmi = FragmentMinum()
        val fpm = FragmentPemesanan()


        loadFragment(fh)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(fh)
                }

                R.id.menu_profil -> {
                    loadFragment(fp)
                }

                R.id.menu_makanan -> {
                    loadFragment(fma)
                }
                R.id.menu_minuman -> {
                    loadFragment(fmi)
                }

                R.id.menu_pemesanan -> {
                    loadFragment(fpm)
                }

                R.id.menu_exit -> {
                    val mBuilder = android.app.AlertDialog.Builder(this@HomeActivity1)
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to exit?")
                        .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                                exitProcess(0)
                            }
                        })
                        .setNegativeButton("No", null)
                        .show()
                }
            }
            true

        }
    }

    private fun loadFragment(fragment: Fragment) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }
}