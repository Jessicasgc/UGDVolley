package com.example.ugdnyakawan


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_registrasi.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    var user: String? = ""
    var pass: String? = ""
    var bundle: Bundle? = null
    var tempUser : String? = ""
    var tempPass: String? = ""

    private val CHANNEL_ID_1 = "channel_notification_01"
    private val notificationId1 = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = ""
        pass = ""
        tempUser = ""
        tempPass = ""
        getBundle()


        supportActionBar?.hide()

        createNotificationChannel()
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
            sendNotificationLogin()
            startActivity(toHome)

        })

        tvRegistrasi.setOnClickListener{
            val intent = Intent(this, RegistrasiActivity::class.java)
            startActivity(intent)
        }


    }

    fun getBundle(){
        bundle= intent?.getBundleExtra("register")

        tempPass = bundle?.getString("username")
        tempUser = bundle?.getString("password")

        inputUsername = findViewById(R.id.tilUsername)
        inputPassword = findViewById(R.id.tilPassword)
        inputUsername?.getEditText()?.setText(tempUser)
        inputPassword?.getEditText()?.setText(tempPass)
        user = tempUser
        pass = tempPass

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(CHANNEL_ID_1, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
        }
    }

    private fun sendNotificationLogin() {
        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val broadcastIntent : Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", "Anda sudah bisa login dari data yang sudah anda registrasikan")
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val bigPictureBitmap = ContextCompat.getDrawable(this, R.drawable.notif)?.toBitmap()
        val bigPictureStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(bigPictureBitmap)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setStyle(bigPictureStyle)
            .setContentTitle("Selamat Berhasil Login")
            .setContentText("Anda sudah bisa menggunakan ")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
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



