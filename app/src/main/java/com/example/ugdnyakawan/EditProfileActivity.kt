package com.example.ugdnyakawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ugdnyakawan.room.User
import com.example.ugdnyakawan.room.UserDao
import com.example.ugdnyakawan.room.UserDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { UserDB(this) }
    private var userId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()



    }

    fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().addUser(
                    User( userId, edit_password.text.toString(),
                        edit_username.text.toString(),
                        edit_email.text.toString(),
                        edit_notelp.text.toString(),
                        edit_tglLahir.text.toString()
                    )
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().updateUser(
                    User(
                        userId, edit_password.text.toString(),
                        edit_username.text.toString(),
                        edit_email.text.toString(),
                        edit_notelp.text.toString(),
                        edit_tglLahir.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getUser()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getUser()
            }
        }
    }

    fun getUser(){
        userId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val users = db.userDao().getUser(userId)[0]
            edit_username.setText(users.username)
            edit_password.setText(users.password)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

