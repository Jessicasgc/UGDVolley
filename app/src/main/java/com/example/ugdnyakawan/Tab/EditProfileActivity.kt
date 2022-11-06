package com.example.ugdnyakawan.Tab

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ugdnyakawan.Constant
import com.example.ugdnyakawan.Notif.NotificationReceiver
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.RegisLogin.MainActivity
import com.example.ugdnyakawan.room.User
import com.example.ugdnyakawan.room.UserDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity() {

    private val CHANNEL_ID_1 = "channel_notification_01"
    private val notificationId1 = 101

    val db by lazy { UserDB(this) }
    private var userId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()
        createNotificationChannel()
    }

    fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().addUser(
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

    fun getUser() {
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(
                CHANNEL_ID_1,
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
        }
    }

    private fun sendNotificationEdit() {
        val intent: Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val broadcastIntent: Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra(
            "toastMessage",
            "Anda sudah bisa login dari data yang sudah anda registrasikan"
        )
        val actionIntent =
            PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentText("Belum bisa fitur edit, msh proses pengembangan")

            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.YELLOW)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }
}
