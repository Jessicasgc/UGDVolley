package com.example.ugdnyakawan

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugdnyakawan.room.User
import com.example.ugdnyakawan.room.UserDB
import kotlinx.android.synthetic.main.activity_usertama.*
import kotlinx.android.synthetic.main.adapter_user.view.*
import kotlinx.android.synthetic.main.content_usertama.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.ugdnyakawan.room.UserDao

class Usertama : AppCompatActivity() {

    val db by lazy { UserDB(this) }
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(arrayListOf(), object :
            UserAdapter.OnAdapterListener{
            override fun onClick(user: User) {
                //Toast.makeText(applicationContext, note.title,
                // Toast.LENGTH_SHORT).show()
                intentEdit(user.id, Constant.TYPE_READ)
            }
            override fun onUpdate(user: User) {
                intentEdit(user.id, Constant.TYPE_UPDATE)
            }
            override fun onDelete(user: User) {
                deleteDialog(user)
            }
        })
        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = userAdapter
        }
    }
    private fun deleteDialog(user: User){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to delete this data From${user.username}?")
            setNegativeButton("Cancel", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            setPositiveButton("Delete", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().deleteUser(user)
                    loadData()
                }
            })
        }
        alertDialog.show()
    }
    override fun onStart() {
        super.onStart()
        loadData()
    }
    //untuk load data yang tersimpan pada database yang sudah create data

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = db.userDao().getUsers()
            Log.d("Userdepan","dbResponse: $users")
            withContext(Dispatchers.Main){
                userAdapter.setData( users )
            }
        }
    }
    fun setupListener() {
        button_create.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    //pick data dari Id yang sebagai primary key
    fun intentEdit(userId : Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", userId)
                .putExtra("intent_type", intentType)
        )
    }

}