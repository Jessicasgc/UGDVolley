package com.example.ugdnyakawan


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_registrasi.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    lateinit var user: String
    lateinit var pass: String
    lateinit var bundle: Bundle
    lateinit var tempUser : String
    lateinit var tempPass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = ""
        pass = ""
        tempUser = ""
        tempPass = ""
        getBundle()


        supportActionBar?.hide()


        inputUsername = findViewById(R.id.tilUsername)
        inputPassword = findViewById(R.id.tilPassword)

        mainLayout = findViewById(R.id.mainLayout)

        val btnClear : Button = findViewById(R.id.btnClear)
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val tvRegistrasi:TextView = findViewById(R.id.tvRegistrasi)
        btnClear.setOnClickListener{
            inputUsername.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")

            Snackbar.make(mainLayout,"Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }

        btnLogin.setOnClickListener (View.OnClickListener{
            var checkLogin = false
            val username : String = inputUsername.getEditText()?.getText().toString()
            val password : String = inputPassword.getEditText()?.getText().toString()

            if(username!=user) {
                inputUsername.setError("Username must be filled correctly")
                checkLogin = false
            }

            if(password!=pass) {
                inputPassword.setError("Password must be filled correctly")
                checkLogin = false
            }

            if(username.isEmpty()){
                inputUsername.setError("Username must be filled with text")
                checkLogin = false
            }

            if(password.isEmpty()){
                inputPassword.setError("Username must be filled with text")
                checkLogin = false
            }

            if (username == user && password == pass) checkLogin = true
            if (!checkLogin) return@OnClickListener
            val toHome = Intent(this@MainActivity, HomeActivity1::class.java)
            startActivity(toHome)

        })

        tvRegistrasi.setOnClickListener{
            val intent = Intent(this, RegistrasiActivity::class.java)
            startActivity(intent)
        }


    }

    fun getBundle(){

        bundle= intent.getBundleExtra("register")!!

        tempPass = bundle.getString("username")!!
        tempUser = bundle.getString("password")!!

        //bundle= intent.getBundleExtra("register")!!
        val bundle:Bundle?=intent.extras

        val tempUser:String? = bundle?.getString("username")
        val tempPass:String? = bundle?.getString("password")!!


        inputUsername = findViewById(R.id.tilUsername)
        inputPassword = findViewById(R.id.tilPassword)
        inputUsername?.getEditText()?.setText(tempUser)
        inputPassword?.getEditText()?.setText(tempPass)

        user = tempUser
        pass = tempPass

        user = tempUser.toString()
        pass = tempPass.toString()


    }

}


