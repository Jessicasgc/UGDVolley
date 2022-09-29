package com.example.ugdnyakawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugdnyakawan.databinding.ActivityRegistrasiBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var iusername: TextInputEditText
    private lateinit var ipassword: TextInputEditText
    private lateinit var iemail: TextInputEditText
    private lateinit var itglLahir: TextInputEditText
    private lateinit var inotelp: TextInputEditText
    var binding : ActivityRegistrasiBinding? = null
    private lateinit var lusername: TextInputLayout
    private lateinit var lpassword: TextInputLayout
    private lateinit var lemail: TextInputLayout
    private lateinit var ltglLahir: TextInputLayout
    private lateinit var lnotelp: TextInputLayout

    //private lateinit var iusername: TextInputEditText
    //private lateinit var ipassword: TextInputEditText
    //private lateinit var iemail: TextInputEditText
    //private lateinit var itglLahir: TextInputEditText
    //private lateinit var inotelp: TextInputEditText
    var binding : ActivityRegistrasiBinding? = null
    //private lateinit var lusername: TextInputLayout
    //private lateinit var lpassword: TextInputLayout
    //private lateinit var lemail: TextInputLayout
    //private lateinit var ltglLahir: TextInputLayout
    //private lateinit var lnotelp: TextInputLayout

    private lateinit var registrasiLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_registrasi)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        iusername = findViewById(R.id.etUsername)
        ipassword = findViewById(R.id.etPassword)
        iemail = findViewById(R.id.etEmail)
        itglLahir = findViewById(R.id.etTglLahir)
        inotelp = findViewById(R.id.etNoTelp)
        registrasiLayout = findViewById(R.id.registrasiLayout)

        lusername = findViewById(R.id.tilUsername)
        lpassword = findViewById(R.id.tilPassword)
        lemail = findViewById(R.id.tilEmail)
        ltglLahir = findViewById(R.id.tilTglLahir)
        lnotelp = findViewById(R.id.tilNoTelp)
=======
        //iusername = findViewById(R.id.etUsername)
        //ipassword = findViewById(R.id.etPassword)
        //iemail = findViewById(R.id.etEmail)
        //itglLahir = findViewById(R.id.etTglLahir)
        //inotelp = findViewById(R.id.etNoTelp)
        registrasiLayout = findViewById(R.id.registrasiLayout)

        //lusername = findViewById(R.id.tilUsername)
        //lpassword = findViewById(R.id.tilPassword)
        //lemail = findViewById(R.id.tilEmail)
        //ltglLahir = findViewById(R.id.tilTglLahir)
        //lnotelp = findViewById(R.id.tilNoTelp)



        val btnRegistrasi: Button = findViewById(R.id.btnRegistrasi)
        val tvLogin: TextView = findViewById(R.id.tvLogin)

        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)
        if (isFirstRun) {
            //show start activity
            startActivity(Intent(this@RegistrasiActivity, Splash::class.java))
            finish()
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).commit()


        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)
        if (isFirstRun) {
            //show start activity
            startActivity(Intent(this@RegistrasiActivity, Splash::class.java))
            finish()
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).commit()


        binding?.tvLogin?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding?.btnClear?.setOnClickListener {
            binding?.tilUsername?.getEditText()?.setText("")
            binding?.tilPassword?.getEditText()?.setText("")
            binding?.tilEmail?.getEditText()?.setText("")
            binding?.tilTglLahir?.getEditText()?.setText("")
            binding?.tilNoTelp?.getEditText()?.setText("")

            Snackbar.make(registrasiLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }


        binding?.btnRegistrasi?.setOnClickListener {

            val intentToLogin = Intent(this, MainActivity::class.java)
            val bundle = Bundle()
            //memasukkan data ke dalam bundle
            val username : String = binding?.tilUsername?.getEditText()?.getText().toString()
            val password : String = binding?.tilPassword?.getEditText()?.getText().toString()
            val email : String = binding?.tilEmail?.getEditText()?.getText().toString()
            val tanggalLahir : String = binding?.tilTglLahir?.getEditText()?.getText().toString()
            val nomorTelepon : String = binding?.tilNoTelp?.getEditText()?.getText().toString()
            //melakukan intent dengan memanggil bundle


            if(username.isEmpty()) {
                binding?.etUsername?.setError("Username must be filled correctly")
            }
            if(password.isEmpty()){
                binding?.etPassword?.setError("Password must be filled correctly")
            }
            if(email.isEmpty()) {
                binding?.etEmail?.setError("Email must be filled correctly")
            }
            if(tanggalLahir.isEmpty()) {
                binding?.etTglLahir?.setError("Tanggal Lahir must be filled correctly")
            }
            if(nomorTelepon.isEmpty()) {
                binding?.etNoTelp?.setError("Nomor Telepon must be filled correctly")
            }else{
                bundle.putString("username", binding?.tilUsername?.getEditText()?.getText().toString())
                bundle.putString("password", binding?.tilPassword?.getEditText()?.getText().toString())
                bundle.putString("email", binding?.tilEmail?.getEditText()?.getText().toString())
                bundle.putString("tanggalLahir", binding?.tilTglLahir?.getEditText()?.getText().toString())
                bundle.putString("nomorTelpon", binding?.tilNoTelp?.getEditText()?.getText().toString())

                intentToLogin.putExtra("register", bundle)
                startActivity(intentToLogin)
        }

        }
}
}