package com.app.olympusforce.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.olympusforce.R
import com.app.olympusforce.view.MainViewModel

class SplashScreenActivity() : AppCompatActivity() {
    private val displayHeigh: Int
        get() = this.resources.displayMetrics.heightPixels

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!load()) {
            val intet = Intent(this@SplashScreenActivity, GActivity::class.java)
            startActivity(intet)
            finish()
        } else {
            val intet = Intent(this@SplashScreenActivity, LA::class.java)
            startActivity(intet)
            finish()
        }
    }

    private fun load(): Boolean {
        fun isNotADB(): Boolean =
            Settings.Global.getString(
                this.contentResolver,
                Settings.Global.ADB_ENABLED
            ) != "1"
        return isNotADB()
    }



}