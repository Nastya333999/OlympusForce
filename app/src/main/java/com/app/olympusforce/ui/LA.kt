package com.app.olympusforce.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.fragment.NavHostFragment
import com.app.olympusforce.R
import com.app.olympusforce.databinding.ActivityLoadingBinding
import com.app.olympusforce.view.MainViewModel


class LA : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()

        viewModel.viewModelInit()
        viewModel._data.observe(this) { url ->
            if (url.isEmpty()) return@observe


            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav)

            val argument = NavArgument.Builder().setDefaultValue(url).build()
            graph.addArgument("url", argument)
            navHostFragment.navController.graph = graph
            binding.frame.visibility = View.VISIBLE
        }
    }

    private fun initUi() {
        val txtAppName = findViewById<TextView>(R.id.txtAppName)

        txtAppName.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.zoom_in_zoom_out
            )
        )
    }

}
