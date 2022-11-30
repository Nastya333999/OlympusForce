package com.app.olympusforce.ui

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.olympusforce.R
import com.app.olympusforce.databinding.ActivityGactivityBinding
import com.app.olympusforce.view.GViewModel

class GActivity : AppCompatActivity() {
    private val viewModel: GViewModel by viewModels()
    private lateinit var binding:ActivityGactivityBinding
    private val adapter = RVAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iO()
        iA()

    }

    private fun iO() {

        viewModel.items.observe(this) {
            adapter.itemList.clear()
            adapter.itemList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.credits.observe(this){
            binding.txtViewBalance.text = "Your credits: $it"
        }

        binding.btnPlay.setOnClickListener {
            viewModel.addPosition()
        }

        binding.btnAddBalance.setOnClickListener{
            viewModel.addMoney()
        }
    }

    private fun iA() {
        val myLinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        binding.cardItemRV.layoutManager = myLinearLayoutManager
        binding.cardItemRV.adapter = adapter

        viewModel.position.observe(this) { positionInt ->
            (binding.cardItemRV.layoutManager as LinearLayoutManager)
                .scrollToPositionWithOffset(
                    positionInt,
                    0
                )
        }
    }


}