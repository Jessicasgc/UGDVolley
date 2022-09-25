package com.example.ugdnyakawan

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.system.exitProcess

class HomeActivity1 : AppCompatActivity() {

    lateinit var Nav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)
        supportActionBar?.hide()

        val fh = FragmentHome()
        val fp = FragmentProfil()
        val fma = FragmentMakan()
        val fmi = FragmentMinum()

    Nav = findViewById(R.id.bottomNavigation)

    loadFragment(fh)
    Nav.setOnNavigationItemReselectedListener{
        when (it.itemId) {
            R.id.menu_home -> {
                loadFragment(fh)

            }

            R.id.menu_profil -> {
                loadFragment(fp)

            }

            R.id.menu_makanan-> {
                loadFragment(fma)

            }
            R.id.menu_minuman -> {
                loadFragment(fmi)

            }
            R.id.menu_exit -> {
                val mBuilder = android.app.AlertDialog.Builder(this@HomeActivity1)
                    .setTitle("Confirm")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", object : DialogInterface.OnClickListener{
                        override fun onClick(dialogInterface: DialogInterface, i: Int){
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
private  fun loadFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container,fragment)
        commit()
    }

}
}