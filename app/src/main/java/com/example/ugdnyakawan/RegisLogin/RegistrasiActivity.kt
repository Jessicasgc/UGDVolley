package com.example.ugdnyakawan.RegisLogin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.ugdnyakawan.Notif.NotificationReceiver
import com.example.ugdnyakawan.R
import com.example.ugdnyakawan.Splash
import com.example.ugdnyakawan.databinding.ActivityRegistrasiBinding
import com.google.android.material.snackbar.Snackbar

class RegistrasiActivity : AppCompatActivity() {
    private var binding : ActivityRegistrasiBinding? = null
    private lateinit var registrasiLayout: ConstraintLayout

    private val CHANNEL_ID_1 = "channel_notification_01"
    private val notificationId1 = 101

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_registrasi)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        createNotificationChannel()
        binding!!.btnRegistrasi.setOnClickListener {
            sendNotificationRegis()
        }

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
                sendNotificationRegis()
                intentToLogin.putExtra("register", bundle)
                startActivity(intentToLogin)
            }

        }
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

    private fun sendNotificationRegis() {
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
            .setContentTitle("Selamat Berhasil Registrasi")
            .setContentText("Anda sudah bisa login dari data yang sudah anda registrasikan")
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