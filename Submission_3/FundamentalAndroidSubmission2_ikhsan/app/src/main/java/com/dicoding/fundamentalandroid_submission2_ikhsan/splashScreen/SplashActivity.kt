package com.dicoding.fundamentalandroid_submission2_ikhsan.splashScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.fundamentalandroid_submission2_ikhsan.R
import com.dicoding.fundamentalandroid_submission2_ikhsan.mainActivity.MainActivity
import com.dicoding.fundamentalandroid_submission2_ikhsan.themeActivity.SettingPreferences
import com.dicoding.fundamentalandroid_submission2_ikhsan.themeActivity.ThemeViewModel
import com.dicoding.fundamentalandroid_submission2_ikhsan.themeActivity.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val pref = SettingPreferences.getInstance(dataStore)
        val themeViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )

        themeViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })

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