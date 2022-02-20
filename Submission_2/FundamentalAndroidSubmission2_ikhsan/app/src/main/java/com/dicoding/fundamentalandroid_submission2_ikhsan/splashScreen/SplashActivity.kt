package com.dicoding.fundamentalandroid_submission2_ikhsan.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.fundamentalandroid_submission2_ikhsan.R
import com.dicoding.fundamentalandroid_submission2_ikhsan.mainActivity.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val actionbar = supportActionBar
        actionbar?.hide()

        val splash = Thread {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        splash.start()
    }
}